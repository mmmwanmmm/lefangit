package com.lefanfs.backend.inner;

import com.lefanfs.backend.dto.ServletUploadFileItem;
import com.lefanfs.backend.dto.UserFileDto;
import com.lefanfs.backend.enums.ImageSizeEnum;
import com.lefanfs.backend.dto.UserFile;
import com.lefanfs.backend.util.FileTypeTest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sh.zj100.common.util.ImageUtil;
import sh.zj100.common.util.ObjectUtil;
import sh.zj100.common.util.RandomIDUtil;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 文件服务实现
 * 
 * @author daniel
 */
@Service
public class FileServiceImpl implements FileService {
	private static final Logger log = Logger.getLogger(FileServiceImpl.class);

	@Autowired
    private TaskExecutor myExecutor;


	@Value("${file.server}")
	private String fileServer;
	@Value("${file.server.rootDir}")
	private String fileServerRootDir;
	@Value("${file.server.tempDir}")
	private String fileServerTempDir;
	@Value("${file.upload.imageMaxSize}")
	private long imageMaxSize;
	@Value("${file.upload.videoMaxSize}")
	private long videoMaxSize;
	@Value("${file.upload.fileMaxSize}")
	private long fileMaxSize;
	@Value("${file.upload.imageType}")
	private String imageType;
	@Value("${file.upload.videoType}")
	private String videoType;
	@Value("${file.upload.fileType}")
	private String fileType;
	@Value("${ffmpeg.path}")
	private String ffmpegPath;

	@Override
	public String makeFilePath(String moduleName, String type) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		String uploadPath = null;
		if (type.equals("resume")) {
			uploadPath = File.separator + moduleName + File.separator + type + File.separator;
		} else if (type.equals("apk")) {
			uploadPath = File.separator + moduleName.replace("/", File.separator) + File.separator;
		} else {
			uploadPath = File.separator + moduleName + File.separator + ymd + File.separator + type + File.separator;
		}
		return uploadPath;
	}

	@Override
	public UserFileDto uploadOneFile(MultipartFile fileItem, long fileType, final String filePath, String userFileDescription, Long userId) {
		// 文件保存目录路径
		String uploadPath = fileServerRootDir + filePath;
		// 创建文件夹
		File dirFile = new File(uploadPath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		OutputStream out = null;
		InputStream in = null;
		String fileName = fileItem.getOriginalFilename();
		String contentType = fileItem.getContentType();
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		// fileName = fileName.substring(0, fileName.lastIndexOf("."));
		String fileUUID = RandomIDUtil.getNewUUID();
		String newFileName = fileUUID + "." + fileExt;
		final String userFilePath = filePath + newFileName;

		UserFileDto userFile = new UserFileDto();
		userFile.setUserId(userId);
		userFile.setFileName(fileName);
		userFile.setUserFileDescription(userFileDescription);
		userFile.setFinalName(newFileName);
		userFile.setUserFilePath(userFilePath);
		userFile.setUserFileSize(fileItem.getSize());
		userFile.setUserFileType(fileExt);
		userFile.setContentType(contentType);
		userFile.setImageSize(ImageSizeEnum.SIZE_DEFAULT.getValue());
		// 上传
		File uploadedFile = null;
		try {
			//FileTypeTest ft = new FileTypeTest();
			uploadedFile = new File(uploadPath, newFileName);
            log.debug("uploadedFile uploadPath newFileName::::::::::"+uploadPath+newFileName);
			String filetype1 = FileTypeTest.getImageFileType(uploadedFile);
			
			out = new FileOutputStream(uploadedFile);
			in = fileItem.getInputStream();
			byte buf[] = new byte[1024];// 可以修改 1024 以提高读取速度
			int length = 0;
			while ((length = in.read(buf)) > 0) {
				out.write(buf, 0, length);
				out.flush();
			}
		} catch (Exception e) {
			log.error("FileServiceImpl.uploadOneFile upload exception:", e);
		} finally {
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				log.error("FileServiceImpl.uploadOneFile upload exception:", e);
			}
		}
		 /*检查图片文件是否是正确的并处理*/
		if (fileType == 1 && uploadedFile.exists())
        {
            String filetype1 = FileTypeTest.getImageFileType(uploadedFile);
            if(StringUtils.isEmpty(filetype1)){
            	uploadedFile.delete();
            	log.error("FileController.uploadFile exception: 文件格式不对");
            	return null;
            }
            String filetype2 = FileTypeTest.getFileByFile(uploadedFile);
            if(StringUtils.isEmpty(filetype2)){
            	uploadedFile.delete();
            	log.error("FileController.uploadFile exception: 文件格式不对");
            	return null;
            }
        }
		// 转码、压缩
		try {
			if (fileType == 1) {// 图片
				for (final String imageSize : ImageSizeEnum.getValues()) {
					myExecutor.execute(new Runnable() {
						@Override
						public void run() {
							try {
								String destPathDir = filePath.replace((File.separator + ImageSizeEnum.SIZE_DEFAULT.getValue() + File.separator), (File.separator + imageSize + File.separator));
								File destDir = new File(fileServerRootDir + destPathDir);
								if (!destDir.exists()) {
									destDir.mkdirs();
								}
								File sourceFile = new File(fileServerRootDir + userFilePath);
								String destFilePath = userFilePath.replace((File.separator + ImageSizeEnum.SIZE_DEFAULT.getValue() + File.separator), (File.separator + imageSize + File.separator));
								File destFile = new File(fileServerRootDir + destFilePath);
								String sizeArr[] = imageSize.split("X");
								ImageUtil.drawImageScale(sourceFile, destFile, Integer.valueOf(sizeArr[0]), Integer.valueOf(sizeArr[1]));
							} catch (Exception e) {
								log.error("FileServiceImpl.uploadOneFile drawImageScale exception:", e);
							}
						}
					});
				}
			} else if (fileType == 2) {// 视频
				try {
					if (this.isFfmpegSupport(fileExt)) {// 以下开始视频转码
						final String videoPicPath = filePath + fileUUID + ".jpg";
						final String finalMp4Path = filePath + fileUUID + ".mp4";
						final String finalFlashPath = filePath + fileUUID + ".flv";
						final String uploadedFilePath = uploadedFile.getAbsolutePath();
						userFile.setVideoPicPath(videoPicPath);
						userFile.setMp4Path(finalMp4Path);
						userFile.setFlashPath(finalFlashPath);
						this.executeCut(ffmpegPath, uploadedFilePath, (fileServerRootDir + videoPicPath));
						for (final VideoSpecEnum videoSpec : VideoSpecEnum.values()) {
							myExecutor.execute(new Runnable() {
								@Override
								public void run() {
									try {
										String codecPathDir = filePath.replace((File.separator + VideoSpecEnum.DEFAULT.getDir() + File.separator),
												(File.separator + videoSpec.getDir() + File.separator));
										File codecDir = new File(fileServerRootDir + codecPathDir);
										if (!codecDir.exists()) {
											codecDir.mkdirs();
										}

										if (!(videoSpec.equals(VideoSpecEnum.DEFAULT) && userFilePath.equals(finalMp4Path))) {
											String mp4CodecPath = finalMp4Path.replace((File.separator + VideoSpecEnum.DEFAULT.getDir() + File.separator),
													(File.separator + videoSpec.getDir() + File.separator));
											this.executeCodec(ffmpegPath, uploadedFilePath, (fileServerRootDir + mp4CodecPath), null, videoSpec);
										}

										if (!(videoSpec.equals(VideoSpecEnum.DEFAULT) && userFilePath.equals(finalFlashPath))) {
											String flashCodecPath = finalFlashPath.replace((File.separator + VideoSpecEnum.DEFAULT.getDir() + File.separator),
													(File.separator + videoSpec.getDir() + File.separator));
											this.executeCodec(ffmpegPath, uploadedFilePath, (fileServerRootDir + flashCodecPath), null, videoSpec);
										}
									} catch (Exception e) {
										log.error("FileServiceImpl.uploadOneFile executeCodecs exception:", e);
									}
								}

								/**
								 * 视频转码
								 * 
								 * @param ffmpegPath
								 *            转码工具的存放路径
								 * @param upFilePath
								 *            用于指定要转换格式的文件,要截图的视频源文件
								 * @param codecFilePath
								 *            格式转换后的的文件保存路径
								 * @param mediaPicPath
								 *            截图保存路径
								 * @param videoSpec
								 *            视频规格
								 * @return
								 * @throws Exception
								 */
								public boolean executeCodec(String ffmpegPath, String upFilePath, String codecFilePath, String mediaPicPath, VideoSpecEnum videoSpec) throws Exception {
									// 创建一个List集合来保存转换视频文件为xx格式的命令
									List<String> convert = new ArrayList<String>();
									convert.add(ffmpegPath); // 添加转换工具路径
									convert.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
									convert.add(upFilePath); // 添加要转换格式的视频文件的路径
									convert.add("-ab"); // 设置音频码率
									convert.add("128");
									// convert.add("-acodec");
									// convert.add("libmp3lame");
									convert.add("-ac"); // 设置声道数
									convert.add("1");
									convert.add("-ar"); // 设置声音的采样频率
									convert.add("22050");
									convert.add("-r"); // 设置帧频
									convert.add("29.97");
									if (videoSpec != null && !videoSpec.equals(VideoSpecEnum.DEFAULT)) {
										convert.add("-b:v"); // 设置视频比特率
										convert.add(videoSpec.getBitrate());
										// convert.add("-qscale"); // 指定转换的质量
										// convert.add(videoSpec.getScale());
										// convert.add("-b"); // 设置视频比特率
										// convert.add("512");
										// convert.add("-s"); // 设置分辨率
										// convert.add(videoSpec.getSize());
									}
									convert.add("-y"); // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
									convert.add(codecFilePath);

									boolean mark = true;
									ProcessBuilder builder = new ProcessBuilder();
									try {
										builder.command(convert);
										builder.redirectErrorStream(true);
										builder.start();
									} catch (Exception e) {
										mark = false;
										e.printStackTrace();
										log.error(e);
									}
									return mark;
								}
							});
						}
					}
				} catch (Exception e) {
					log.error("FileServiceImpl.uploadOneFile codec & cut exception:", e);
				}
			}
			Long userFileId = this.addUserFile(userFile);
			userFile.setUserFileId(userFileId);
		} catch (Exception e) {
			log.error("FileServiceImpl.uploadOneFile exception:", e);
		}
		return userFile;
	}

	/**
	 * 视频截图
	 * 
	 * @param ffmpegPath
	 *            转码工具的存放路径
	 * @param upFilePath
	 *            用于指定要转换格式的文件,要截图的视频源文件
	 * @param mediaPicPath
	 *            截图保存路径
	 * @return
	 * @throws Exception
	 */
	public boolean executeCut(String ffmpegPath, String upFilePath, String mediaPicPath) throws Exception {
		// 创建一个List集合来保存从视频中截取图片的命令
		List<String> cutpic = new ArrayList<String>();
		cutpic.add(ffmpegPath);
		cutpic.add("-i");
		cutpic.add(upFilePath); // 同上（指定的文件即可以是转换为xxx格式之前的文件，也可以是转换的xxx文件）
		cutpic.add("-y");
		cutpic.add("-f");
		cutpic.add("image2");
		cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
		cutpic.add("0"); // 添加起始时间为第17秒
		cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
		cutpic.add("0.001"); // 添加持续时间为1毫秒
		cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
		cutpic.add("400*300"); // 添加截取的图片大小为350*240
		cutpic.add(mediaPicPath); // 添加截取的图片的保存路径

		boolean mark = true;
		ProcessBuilder builder = new ProcessBuilder();
		try {
			builder.command(cutpic);
			builder.redirectErrorStream(true);
			// 如果此属性为 true，则任何由通过此对象的 start()
			// 方法启动的后续子进程生成的错误输出都将与标准输出合并，
			// 因此两者均可使用 Process.getInputStream()
			// 方法读取。这使得关联错误消息和相应的输出变得更容易
			builder.start();
		} catch (Exception e) {
			mark = false;
			e.printStackTrace();
			log.error(e);
		}
		return mark;
	}

	@Deprecated
	@Override
	public List<UserFileDto> uploadFile(String userFileDescription, String filePath, Long userId, List<FileItem> fileItemList) {
		List<UserFileDto> filePathList = new ArrayList<UserFileDto>();
		// 文件保存目录路径
		String uploadPath = fileServerRootDir + filePath;
		// 创建文件夹
		File dirFile = new File(uploadPath);
        System.out.println("-----------------uploadPath-----------------------"+uploadPath);
        if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		for (FileItem item : fileItemList) {
			OutputStream out = null;
			InputStream in = null;
			String fileName = item.getName();
			String contentType = item.getContentType();
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
			String fileUUID = RandomIDUtil.getNewUUID();
			String newFileName = fileUUID + "." + fileExt;
//            if("apk".equals(fileExt)){
//                newFileName =fileName + "." + fileExt;
//            }
			UserFileDto userFile = new UserFileDto();
			userFile.setUserId(userId);
			userFile.setFileName(fileName);
			userFile.setUserFileDescription(userFileDescription);
			userFile.setFinalName(newFileName);
			userFile.setUserFilePath(filePath + newFileName);
			userFile.setUserFileSize(item.getSize());
			userFile.setUserFileType(fileExt);
			userFile.setContentType(contentType);
			userFile.setImageSize(ImageSizeEnum.SIZE_DEFAULT.getValue());
			try {
				File uploadedFile = new File(uploadPath, newFileName);
				out = new FileOutputStream(uploadedFile);
				in = item.getInputStream();
				byte buf[] = new byte[1024];// 可以修改 1024 以提高读取速度
				int length = 0;
				while ((length = in.read(buf)) > 0) {
					out.write(buf, 0, length);
					out.flush();
				}
                if("epeit".equals(fileName)){
                    newFileName =fileName + "." + fileExt;
                }

				Long userFileId =null;
                if("epeit".equals(fileName)&&uploadPath.contains("appfile")){

                }else{
                    userFileId = this.addUserFile(userFile);
                    userFile.setUserFileId(userFileId);
                }
				filePathList.add(userFile);
			} catch (Exception e) {
				log.error("FileServiceImpl.uploadFile exception:", e);
			} finally {
				try {
					out.close();
					in.close();
				} catch (IOException e) {
					log.error("FileServiceImpl.uploadFile exception:", e);
				}
			}
		}
		return filePathList;
	}

	private boolean isFfmpegSupport(String fileType) {// asx,asf,mpg,wmv,3gp,mp4,mov,avi,flv
		if ("asx".equalsIgnoreCase(fileType) || "asf".equalsIgnoreCase(fileType) || "mpg".equalsIgnoreCase(fileType) || "wmv".equalsIgnoreCase(fileType) || "3gp".equalsIgnoreCase(fileType)
				|| "mp4".equalsIgnoreCase(fileType) || "mov".equalsIgnoreCase(fileType) || "avi".equalsIgnoreCase(fileType) || "flv".equalsIgnoreCase(fileType)) {
			return true;
		}
		return false;
	}

	public enum VideoSpecEnum {
		/**
		 * 流畅版
		 */
		DEFAULT("default", null, null, null),
		/**
		 * 流畅版
		 */
		FD("fd", "320x180", "10", "256k"),
		/**
		 * 标清版
		 */
		SD("sd", "720x480", "6", "512k"),
		/**
		 * 高清版
		 */
		HD("hd", "1280x720", "3", "1000k"),
		/**
		 * 全高清版
		 */
		FHD("fhd", "1920x1080", "1", "1500k");

		/**
		 * 文件目录
		 */
		private final String dir;

		/**
		 * 分辨率
		 */
		private final String size;
		/**
		 * 质量
		 */
		private final String scale;
		/**
		 * 比特率
		 */
		private final String bitrate;

		private VideoSpecEnum(String _dir, String _size, String _scale, String _bitrate) {
			this.dir = _dir;
			this.size = _size;
			this.scale = _scale;
			this.bitrate = _bitrate;
		}

		public String getDir() {
			return dir;
		}

		public String getSize() {
			return size;
		}

		public String getScale() {
			return scale;
		}

		public String getBitrate() {
			return bitrate;
		}

	}

	/**
	 * 上传mht或二维码文件，因需对数据库单独处理
	 * 
	 * @param filePath
	 * @param userId
	 * @param fileItemList
	 * @param fileId
	 * @return
	 */
	@Override
	public List<UserFileDto> uploadFileToMht(String filePath, Long userId, List<FileItem> fileItemList, Long fileId) {
		List<UserFileDto> filePathList = new ArrayList<UserFileDto>();
		// 文件保存目录路径
		String uploadPath = fileServerRootDir + filePath;
		// 创建文件夹
		File dirFile = new File(uploadPath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		for (FileItem item : fileItemList) {
			OutputStream out = null;
			InputStream in = null;
			String fileName = item.getName();
			try {
				fileName.getBytes("utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String contentType = item.getContentType();
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
			// String newFileName = RandomIDUtil.getNewUUID() + "." + fileExt;

			String newFileName = fileName + "." + fileExt;
			// 如果文件ID不为空，则不需要对userFile表再次对数据库操作
			if (fileId.equals(0L)) {

				UserFileDto userFile = new UserFileDto();
				userFile.setUserId(userId);
				userFile.setFileName(fileName);
				userFile.setFinalName(newFileName);
				userFile.setUserFilePath(filePath + newFileName);
				userFile.setUserFileSize(item.getSize());
				userFile.setUserFileType(fileExt);
				userFile.setContentType(contentType);
				userFile.setImageSize(ImageSizeEnum.SIZE_DEFAULT.getValue());

				Long userFileId = this.addUserFile(userFile);
				userFile.setUserFileId(userFileId);
				filePathList.add(userFile);
			}
			try {
				File uploadedFile = new File(uploadPath, newFileName);
				out = new FileOutputStream(uploadedFile);
				in = item.getInputStream();
				byte buf[] = new byte[1024];// 可以修改 1024 以提高读取速度
				int length = 0;
				while ((length = in.read(buf)) > 0) {
					out.write(buf, 0, length);
					out.flush();
				}

			} catch (Exception e) {
				log.error("FileServiceImpl.uploadFile exception:", e);
			} finally {
				try {
					out.close();
					in.close();
				} catch (IOException e) {
					log.error("FileServiceImpl.uploadFile exception:", e);
				}
			}
		}
		return filePathList;
	}

	@Override
	public void downloadFile(UserFileDto userFile, HttpServletResponse response) {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			String downLoadPath = fileServerRootDir + File.separator + userFile.getUserFilePath();
			File downLoadFile = new File(downLoadPath);
			String fileName = userFile.getFileName() + "." + userFile.getUserFileType();
			response.reset();
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1"));
			response.setContentType(userFile.getContentType() + ";charset=UTF-8");
			response.setHeader("Content-Length", String.valueOf(userFile.getUserFileSize()));
			in = new BufferedInputStream(new FileInputStream(downLoadFile));
			out = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int length = 0;
			while ((length = in.read(buff)) > 0) {
				out.write(buff, 0, length);
				out.flush();
			}
		} catch (Exception e) {
			log.error("FileServiceImpl.downloadFile exception:", e);
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				log.error("FileServiceImpl.downloadFile exception:", e);
			}
		}
	}

	@Override
	public ServletUploadFileItem getUploadFileItem(HttpServletRequest request) throws Exception {
		String uploadTempPath = fileServerTempDir;
		uploadTempPath += File.separator + "temp" + File.separator;
		// 创建临时文件夹
        System.out.println("-------------------------------------------------------"+uploadTempPath);
        File dirTempFile = new File(uploadTempPath);
		if (!dirTempFile.exists()) {
			dirTempFile.mkdirs();
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(5 * 1024 * 1024); // 设定使用内存超过5M时，将产生临时文件并存储于临时目录中。
		factory.setRepository(new File(uploadTempPath)); // 设定存储临时文件的目录。
		ServletUploadFileItem upload = new ServletUploadFileItem(factory, request);

		upload.setHeaderEncoding("utf-8");
		return upload;
	}

	public Long addUserFile(UserFileDto userFileDto) {
		UserFile file = new UserFile();
		ObjectUtil.copyProperties(file, userFileDto);
		//this.userFileMapper.insert(file);
		return file.getUserFileId();
	}

	@Deprecated
	@Override
	public void makeFixImages(List<UserFileDto> userFiles, List<String> imgList) {
		final UserFileDto defaultFile = userFiles.get(0);
		List<UserFileDto> fileList = new ArrayList<UserFileDto>();
		if (imgList == null) {
			imgList = ImageSizeEnum.getValues();
		}
		for (String imgSize : imgList) {
			String newFileName = defaultFile.getFinalName();
			UserFileDto userFile = new UserFileDto();
			userFile.setUserId(defaultFile.getUserId());
			userFile.setFileName(defaultFile.getFileName());
			userFile.setFinalName(newFileName);
			String newFilePath = defaultFile.getUserFilePath();
			newFilePath = newFilePath.substring(0, newFilePath.lastIndexOf(File.separator)) + File.separator;
			newFilePath = newFilePath.replace(ImageSizeEnum.SIZE_DEFAULT.getValue(), imgSize);
			userFile.setUserFilePath(newFilePath + newFileName);
			userFile.setUserFileSize(defaultFile.getUserFileSize());
			userFile.setUserFileType(defaultFile.getUserFileType());
			userFile.setContentType(defaultFile.getContentType());
			userFile.setImageSize(imgSize);
			File destFile = new File(this.fileServerRootDir + newFilePath);
			if (!destFile.exists()) {
				destFile.mkdirs();
			}
			this.addUserFile(userFile);
			fileList.add(userFile);
		}
		for (final UserFileDto userFile : fileList) {
			// service.submit(new Runnable() {
			myExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						File sourceFile = new File(fileServerRootDir + defaultFile.getUserFilePath());
						File destFile = new File(fileServerRootDir + userFile.getUserFilePath());
						String size[] = userFile.getImageSize().split("X");
						ImageUtil.drawImageScale(sourceFile, destFile, Integer.valueOf(size[0]), Integer.valueOf(size[1]));
					} catch (Exception e) {
						log.error("FileServiceImpl.makeFixImages.run exception:", e);
					}
				}
			});
		}
	}

	@Override
	public UserFileDto getUserFileByFileId(Long fileId) {
		UserFileDto fileDto = new UserFileDto();
		//UserFile file = this.userFileMapper.selectByPrimaryKey(fileId);
		//ObjectUtil.copyProperties(fileDto, file);
		return fileDto;
	}

	@Override
	public boolean validateImageFile(List<FileItem> fileItems) {
		for (FileItem file : fileItems) {
			long maxSize = file.getSize();
			String ext = file.getName();
			ext = ext.substring(ext.lastIndexOf(".") + 1).toLowerCase();
			if (maxSize > this.imageMaxSize * 1024 * 1024) {
				return false;
			}
			if (!this.imageType.contains(ext)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean validateVideoFile(List<FileItem> fileItems) {
		for (FileItem file : fileItems) {
			long maxSize = file.getSize();
			String ext = file.getName();
			ext = ext.substring(ext.lastIndexOf(".") + 1).toLowerCase();
			if (maxSize > this.videoMaxSize * 1024 * 1024) {
				return false;
			}
			if (!this.videoType.contains(ext)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean validateFile(List<FileItem> fileItems) {
		for (FileItem file : fileItems) {
			long maxSize = file.getSize();
			String ext = file.getName();
			ext = ext.substring(ext.lastIndexOf(".") + 1).toLowerCase();
			if (maxSize > this.fileMaxSize * 1024 * 1024) {
				return false;
			}
			if (!this.fileType.contains(ext)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean validateApkFile(List<FileItem> fileItems) {
		for (FileItem file : fileItems) {
			// long maxSize = file.getSize();
			String ext = file.getName();
			ext = ext.substring(ext.lastIndexOf(".") + 1).toLowerCase();
			if (!this.fileType.contains(ext)) {
				return false;
			}
		}
		return true;
	}

	// public String getFileServerRootDir() {
	// return fileServerRootDir;
	// }
	//
	// public void setFileServerRootDir(String fileServerRootDir) {
	// this.fileServerRootDir = fileServerRootDir;
	// }
	//
	// public String getFileServerTempDir() {
	// return fileServerTempDir;
	// }
	//
	// public void setFileServerTempDir(String fileServerTempDir) {
	// this.fileServerTempDir = fileServerTempDir;
	// }
	//
	// public long getImageMaxSize() {
	// return imageMaxSize;
	// }
	//
	// public void setImageMaxSize(long imageMaxSize) {
	// this.imageMaxSize = imageMaxSize;
	// }
	//
	// public long getFileMaxSize() {
	// return fileMaxSize;
	// }
	//
	// public void setFileMaxSize(long fileMaxSize) {
	// this.fileMaxSize = fileMaxSize;
	// }
	//
	// public String getImageType() {
	// return imageType;
	// }
	//
	// public void setImageType(String imageType) {
	// this.imageType = imageType;
	// }
	//
	// public String getFileType() {
	// return fileType;
	// }
	//
	// public String getFileServer() {
	// return fileServer;
	// }
	//
	// public void setFileServer(String fileServer) {
	// this.fileServer = fileServer;
	// }
	//
	// public void setFileType(String fileType) {
	// this.fileType = fileType;
	// }
	//
	// public UserFileMapper getUserFileMapper() {
	// return userFileMapper;
	// }
	//
	// public void setUserFileMapper(UserFileMapper userFileMapper) {
	// this.userFileMapper = userFileMapper;
	// }
	//
	// public TaskExecutor getMyExecutor() {
	// return myExecutor;
	// }
	//
	// public void setMyExecutor(TaskExecutor myExecutor) {
	// this.myExecutor = myExecutor;
	// }
	//
	// public String getFfmpegPath() {
	// return ffmpegPath;
	// }
	//
	// public void setFfmpegPath(String ffmpegPath) {
	// this.ffmpegPath = ffmpegPath;
	// }
	//
	// public long getVideoMaxSize() {
	// return videoMaxSize;
	// }
	//
	// public void setVideoMaxSize(long videoMaxSize) {
	// this.videoMaxSize = videoMaxSize;
	// }
	//
	// public String getVideoType() {
	// return videoType;
	// }
	//
	// public void setVideoType(String videoType) {
	// this.videoType = videoType;
	// }

	@Override
	public List<UserFileDto> uploadApkFile(String filePath, Long userId, List<FileItem> fileItemList) {
		List<UserFileDto> filePathList = new ArrayList<UserFileDto>();
		// 文件保存目录路径
		String uploadPath = fileServerRootDir + filePath;
		// 创建文件夹
		File dirFile = new File(uploadPath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		for (FileItem item : fileItemList) {
			OutputStream out = null;
			InputStream in = null;
			String fileName = item.getName();
			try {
				fileName.getBytes("utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String contentType = item.getContentType();
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
			// String newFileName = RandomIDUtil.getNewUUID() + "." + fileExt;

			String newFileName = fileName + "." + fileExt;
			UserFileDto userFile = new UserFileDto();
			userFile.setUserId(userId);
			userFile.setFileName(fileName);
			userFile.setFinalName(newFileName);
			filePath = filePath.replace("\\", "/");
			userFile.setUserFilePath(filePath + newFileName);
			userFile.setUserFileSize(item.getSize());
			userFile.setUserFileType(fileExt);
			userFile.setContentType(contentType);
			userFile.setImageSize(ImageSizeEnum.SIZE_DEFAULT.getValue());

			Long userFileId = this.addUserFile(userFile);
			userFile.setUserFileId(userFileId);
			filePathList.add(userFile);

			try {
				File uploadedFile = new File(uploadPath, newFileName);
				out = new FileOutputStream(uploadedFile);
				in = item.getInputStream();
				byte buf[] = new byte[1024];// 可以修改 1024 以提高读取速度
				int length = 0;
				while ((length = in.read(buf)) > 0) {
					out.write(buf, 0, length);
					out.flush();
				}

			} catch (Exception e) {
				log.error("FileServiceImpl.uploadFile exception:", e);
			} finally {
				try {
					out.close();
					in.close();
				} catch (IOException e) {
					log.error("FileServiceImpl.uploadFile exception:", e);
				}
			}

		}

		return filePathList;
	}

	/**
	 * 生成app下载地址二维码，并上传二维码
	 * 
	 * @return
	 */
	/*@Override
	public String createQRcode(String content, String fileName) {
		// Long appVesionId = appVersion.getVersionId();
		// String separator =
		// System.getProperties().getProperty("file.separator");
		String uploadUrl = fileServerRootDir + "/" + "app" + "/" + "images";
		File dirFile = new File(uploadUrl);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		// String fileName = appVesionId + "_2.png";
		String imgPath = uploadUrl + "/" + fileName;
		String imageUrl = fileServer + "/" + "app" + "/" + "images" + "/" + fileName;
		// 生成二维码文件
		TwoDimensionCode handler = new TwoDimensionCode();
		try {
			handler.encoderQRCode(content, imgPath, "png");
			// String contentString = handler.decoderQRCode(imgPath);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return imageUrl;
	}*/

    @Override
    public UserFileDto uploadWeiZhanOneFile(String fileName,String cutPath,long fileSize, long fileType, final String filePath, String userFileDescription, Long userId) {
        String wzFileType = fileName.substring(fileName.lastIndexOf(".")+1);

        // 文件保存目录路径
        String uploadPath = fileServerRootDir + filePath;
        // 创建文件夹
        File dirFile = new File(uploadPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        OutputStream out = null;
        InputStream in = null;
//        String fileName = "fd";
        String contentType = "image/jpeg";
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        // fileName = fileName.substring(0, fileName.lastIndexOf("."));
        String fileUUID = RandomIDUtil.getNewUUID();
        String newFileName = fileUUID + "." + fileExt;
        final String userFilePath = filePath + newFileName;

        UserFileDto userFile = new UserFileDto();
        userFile.setUserId(userId);
        userFile.setFileName(fileName);
        userFile.setUserFileDescription(userFileDescription);
        userFile.setFinalName(newFileName);
        userFile.setUserFilePath(userFilePath);
        userFile.setUserFileSize(fileSize);
        userFile.setUserFileType(wzFileType);
        userFile.setContentType(contentType);
        userFile.setImageSize(ImageSizeEnum.SIZE_DEFAULT.getValue());
        // 上传
        File uploadedFile = null;
        try {
            //FileTypeTest ft = new FileTypeTest();
            uploadedFile = new File(uploadPath, newFileName);
            String filetype1 = FileTypeTest.getImageFileType(uploadedFile);

            out = new FileOutputStream(uploadedFile);
            in = new FileInputStream(cutPath);
            byte buf[] = new byte[1024];// 可以修改 1024 以提高读取速度
            int length = 0;
            while ((length = in.read(buf)) > 0) {
                out.write(buf, 0, length);
                out.flush();
            }
        } catch (Exception e) {
            log.error("FileServiceImpl.uploadOneFile upload exception:", e);
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                log.error("FileServiceImpl.uploadOneFile upload exception:", e);
            }
        }
		 /*检查图片文件是否是正确的并处理*/
        if (fileType == 1 && uploadedFile.exists())
        {
            String filetype1 = FileTypeTest.getImageFileType(uploadedFile);
            if(StringUtils.isEmpty(filetype1)){
                uploadedFile.delete();
                log.error("FileController.uploadFile exception: 文件格式不对");
                return null;
            }
            String filetype2 = FileTypeTest.getFileByFile(uploadedFile);
            if(StringUtils.isEmpty(filetype2)){
                uploadedFile.delete();
                log.error("FileController.uploadFile exception: 文件格式不对");
                return null;
            }
        }
        // 转码、压缩
        try {
            if (fileType == 1) {// 图片
                for (final String imageSize : ImageSizeEnum.getValues()) {
                    myExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String destPathDir = filePath.replace((File.separator + ImageSizeEnum.SIZE_DEFAULT.getValue() + File.separator), (File.separator + imageSize + File.separator));
                                File destDir = new File(fileServerRootDir + destPathDir);
                                if (!destDir.exists()) {
                                    destDir.mkdirs();
                                }
                                File sourceFile = new File(fileServerRootDir + userFilePath);
                                String destFilePath = userFilePath.replace((File.separator + ImageSizeEnum.SIZE_DEFAULT.getValue() + File.separator), (File.separator + imageSize + File.separator));
                                File destFile = new File(fileServerRootDir + destFilePath);
                                String sizeArr[] = imageSize.split("X");
                                ImageUtil.drawImageScale(sourceFile, destFile, Integer.valueOf(sizeArr[0]), Integer.valueOf(sizeArr[1]));
                            } catch (Exception e) {
                                log.error("FileServiceImpl.uploadOneFile drawImageScale exception:", e);
                            }
                        }
                    });
                }
            } else if (fileType == 2) {// 视频
                try {
                    if (this.isFfmpegSupport(fileExt)) {// 以下开始视频转码
                        final String videoPicPath = filePath + fileUUID + ".jpg";
                        final String finalMp4Path = filePath + fileUUID + ".mp4";
                        final String finalFlashPath = filePath + fileUUID + ".flv";
                        final String uploadedFilePath = uploadedFile.getAbsolutePath();
                        userFile.setVideoPicPath(videoPicPath);
                        userFile.setMp4Path(finalMp4Path);
                        userFile.setFlashPath(finalFlashPath);
                        this.executeCut(ffmpegPath, uploadedFilePath, (fileServerRootDir + videoPicPath));
                        for (final VideoSpecEnum videoSpec : VideoSpecEnum.values()) {
                            myExecutor.execute(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        String codecPathDir = filePath.replace((File.separator + VideoSpecEnum.DEFAULT.getDir() + File.separator),
                                                (File.separator + videoSpec.getDir() + File.separator));
                                        File codecDir = new File(fileServerRootDir + codecPathDir);
                                        if (!codecDir.exists()) {
                                            codecDir.mkdirs();
                                        }

                                        if (!(videoSpec.equals(VideoSpecEnum.DEFAULT) && userFilePath.equals(finalMp4Path))) {
                                            String mp4CodecPath = finalMp4Path.replace((File.separator + VideoSpecEnum.DEFAULT.getDir() + File.separator),
                                                    (File.separator + videoSpec.getDir() + File.separator));
                                            this.executeCodec(ffmpegPath, uploadedFilePath, (fileServerRootDir + mp4CodecPath), null, videoSpec);
                                        }

                                        if (!(videoSpec.equals(VideoSpecEnum.DEFAULT) && userFilePath.equals(finalFlashPath))) {
                                            String flashCodecPath = finalFlashPath.replace((File.separator + VideoSpecEnum.DEFAULT.getDir() + File.separator),
                                                    (File.separator + videoSpec.getDir() + File.separator));
                                            this.executeCodec(ffmpegPath, uploadedFilePath, (fileServerRootDir + flashCodecPath), null, videoSpec);
                                        }
                                    } catch (Exception e) {
                                        log.error("FileServiceImpl.uploadOneFile executeCodecs exception:", e);
                                    }
                                }

                                /**
                                 * 视频转码
                                 *
                                 * @param ffmpegPath
                                 *            转码工具的存放路径
                                 * @param upFilePath
                                 *            用于指定要转换格式的文件,要截图的视频源文件
                                 * @param codecFilePath
                                 *            格式转换后的的文件保存路径
                                 * @param mediaPicPath
                                 *            截图保存路径
                                 * @param videoSpec
                                 *            视频规格
                                 * @return
                                 * @throws Exception
                                 */
                                public boolean executeCodec(String ffmpegPath, String upFilePath, String codecFilePath, String mediaPicPath, VideoSpecEnum videoSpec) throws Exception {
                                    // 创建一个List集合来保存转换视频文件为xx格式的命令
                                    List<String> convert = new ArrayList<String>();
                                    convert.add(ffmpegPath); // 添加转换工具路径
                                    convert.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
                                    convert.add(upFilePath); // 添加要转换格式的视频文件的路径
                                    convert.add("-ab"); // 设置音频码率
                                    convert.add("128");
                                    // convert.add("-acodec");
                                    // convert.add("libmp3lame");
                                    convert.add("-ac"); // 设置声道数
                                    convert.add("1");
                                    convert.add("-ar"); // 设置声音的采样频率
                                    convert.add("22050");
                                    convert.add("-r"); // 设置帧频
                                    convert.add("29.97");
                                    if (videoSpec != null && !videoSpec.equals(VideoSpecEnum.DEFAULT)) {
                                        convert.add("-b:v"); // 设置视频比特率
                                        convert.add(videoSpec.getBitrate());
                                        // convert.add("-qscale"); // 指定转换的质量
                                        // convert.add(videoSpec.getScale());
                                        // convert.add("-b"); // 设置视频比特率
                                        // convert.add("512");
                                        // convert.add("-s"); // 设置分辨率
                                        // convert.add(videoSpec.getSize());
                                    }
                                    convert.add("-y"); // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
                                    convert.add(codecFilePath);

                                    boolean mark = true;
                                    ProcessBuilder builder = new ProcessBuilder();
                                    try {
                                        builder.command(convert);
                                        builder.redirectErrorStream(true);
                                        builder.start();
                                    } catch (Exception e) {
                                        mark = false;
                                        e.printStackTrace();
                                        log.error(e);
                                    }
                                    return mark;
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    log.error("FileServiceImpl.uploadOneFile codec & cut exception:", e);
                }
            }
            Long userFileId = this.addUserFile(userFile);
            userFile.setUserFileId(userFileId);
        } catch (Exception e) {
            log.error("FileServiceImpl.uploadOneFile exception:", e);
        }
        return userFile;
    }

    @Override
    public String uploadWeiZhanCutFile(String sourceSrc, int left, int top, int width, int height) {
        String fileType = sourceSrc.substring(sourceSrc.lastIndexOf(".")+1);
        FileInputStream is = null;
        ImageInputStream iis = null;
        String cutPath = fileServerRootDir+sourceSrc.substring(0,sourceSrc.lastIndexOf("."))+"-cut."+fileType;
        try {
            is = new FileInputStream(fileServerRootDir+ File.separator+sourceSrc);
            log.info("sourceFilePath============"+fileServer+ File.separator+sourceSrc);
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(fileType);
            ImageReader reader = it.next();
            iis = ImageIO.createImageInputStream(is);
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            Rectangle rect = new Rectangle(left, top, width, height);
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0, param);
            ImageIO.write(bi, fileType, new File(cutPath));
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
                try {
                    if (is != null)
                    is.close();
                    if (iis != null)
                        iis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
        return cutPath;
    }

    @Override
	public String uploadNetImage(String prefixPath, String sourcePath) {
		String imagePath = null;
		try {
			URL url = new URL(sourcePath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			inStream.close();
			byte[] img = outStream.toByteArray();
			File folder = new File(fileServerRootDir + File.separator + prefixPath);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			String imageName = RandomIDUtil.getNewUUID() + ".jpg";
			imagePath = prefixPath + File.separator + imageName;
			String downLoadPath = fileServerRootDir + File.separator + imagePath;
			File file = new File(downLoadPath);
			FileOutputStream fops = new FileOutputStream(file);
			fops.write(img);
			fops.flush();
			fops.close();
		} catch (Exception e) {
			log.error("FileServiceImpl.downLoadNetImage exception:", e);
		}
		return imagePath;
	}
}
