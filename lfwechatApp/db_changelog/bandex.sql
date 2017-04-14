
INSERT INTO sys_menu (menu_name,menu_code,menu_url,pid,sort,is_delete,is_show,menu_summary,depth)
VALUES 
('订单列表', 'com.bandex.backend.web.BackendOrderController.list', '/order/list' ,(select DISTINCT(ss.id)  from sys_menu as ss where ss.menu_code='order') ,1000,0,1,NULL,2);

INSERT INTO sys_menu (menu_name,menu_code,menu_url,pid,sort,is_delete,is_show,menu_summary,depth)
VALUES 
('编辑订单', 'com.bandex.backend.web.BackendOrderController.edit', '/order/edit' ,(select DISTINCT(ss.id)  from sys_menu as ss where ss.menu_code='com.bandex.backend.web.BackendOrderController.list') ,1,0,0,NULL,3);

INSERT INTO sys_menu (menu_name,menu_code,menu_url,pid,sort,is_delete,is_show,menu_summary,depth)
VALUES 
('更新订单', 'com.bandex.backend.web.BackendOrderController.update', '/order/update' ,(select DISTINCT(ss.id)  from sys_menu as ss where ss.menu_code='com.bandex.backend.web.BackendOrderController.list') ,1,0,0,NULL,3);


-----------------------------------------
INSERT INTO sys_menu (menu_name,menu_code,menu_url,pid,sort,is_delete,is_show,menu_summary,depth)
VALUES 
('商品列表', 'com.bandex.backend.web.BackendProductController.productList', '/product/productList' ,(select DISTINCT(ss.id)  from sys_menu as ss where ss.menu_code='product') ,1000,0,1,NULL,2);

INSERT INTO sys_menu (menu_name,menu_code,menu_url,pid,sort,is_delete,is_show,menu_summary,depth)
VALUES 
('添加商品', 'com.bandex.backend.web.BackendProductController.createProduct', '/product/createProduct' ,(select DISTINCT(ss.id)  from sys_menu as ss where ss.menu_code='com.bandex.backend.web.BackendProductController.productList') ,1,0,0,NULL,3);

INSERT INTO sys_menu (menu_name,menu_code,menu_url,pid,sort,is_delete,is_show,menu_summary,depth)
VALUES 
('编辑商品', 'com.bandex.backend.web.BackendProductController.editProduct', '/product/editProduct' ,(select DISTINCT(ss.id)  from sys_menu as ss where ss.menu_code='com.bandex.backend.web.BackendProductController.productList') ,1,0,0,NULL,3);

INSERT INTO sys_menu (menu_name,menu_code,menu_url,pid,sort,is_delete,is_show,menu_summary,depth)
VALUES 
('保存商品', 'com.bandex.backend.web.BackendProductController.saveProduct', '/product/saveProduct' ,(select DISTINCT(ss.id)  from sys_menu as ss where ss.menu_code='com.bandex.backend.web.BackendProductController.productList') ,1,0,0,NULL,3);

INSERT INTO sys_menu (menu_name,menu_code,menu_url,pid,sort,is_delete,is_show,menu_summary,depth)
VALUES 
('更新状态', 'com.bandex.backend.web.BackendProductController.changeStatus', '/product/changeStatus' ,(select DISTINCT(ss.id)  from sys_menu as ss where ss.menu_code='com.bandex.backend.web.BackendProductController.productList') ,1,0,0,NULL,3);

---------------------------------
INSERT INTO sys_menu (menu_name,menu_code,menu_url,pid,sort,is_delete,is_show,menu_summary,depth)
VALUES 
('活动列表', 'com.bandex.backend.web.BackendActivityController.productList', '/activity/productList' ,(select DISTINCT(ss.id)  from sys_menu as ss where ss.menu_code='product') ,1000,0,1,NULL,2);

INSERT INTO sys_menu (menu_name,menu_code,menu_url,pid,sort,is_delete,is_show,menu_summary,depth)
VALUES 
('添加活动', 'com.bandex.backend.web.BackendActivityController.createProduct', '/activity/createProduct' ,(select DISTINCT(ss.id)  from sys_menu as ss where ss.menu_code='com.bandex.backend.web.BackendActivityController.productList') ,1,0,0,NULL,3);

INSERT INTO sys_menu (menu_name,menu_code,menu_url,pid,sort,is_delete,is_show,menu_summary,depth)
VALUES 
('编辑活动', 'com.bandex.backend.web.BackendActivityController.editProduct', '/activity/editProduct' ,(select DISTINCT(ss.id)  from sys_menu as ss where ss.menu_code='com.bandex.backend.web.BackendActivityController.productList') ,1,0,0,NULL,3);

INSERT INTO sys_menu (menu_name,menu_code,menu_url,pid,sort,is_delete,is_show,menu_summary,depth)
VALUES 
('保存活动', 'com.bandex.backend.web.BackendActivityController.saveProduct', '/activity/saveProduct' ,(select DISTINCT(ss.id)  from sys_menu as ss where ss.menu_code='com.bandex.backend.web.BackendActivityController.productList') ,1,0,0,NULL,3);

INSERT INTO sys_menu (menu_name,menu_code,menu_url,pid,sort,is_delete,is_show,menu_summary,depth)
VALUES 
('更新状态', 'com.bandex.backend.web.BackendActivityController.changeStatus', '/activity/changeStatus' ,(select DISTINCT(ss.id)  from sys_menu as ss where ss.menu_code='com.bandex.backend.web.BackendActivityController.productList') ,1,0,0,NULL,3);

---------------------------------
INSERT INTO sys_menu (menu_name,menu_code,menu_url,pid,sort,is_delete,is_show,menu_summary,depth)
VALUES 
('广告列表', 'com.bandex.backend.web.BackendAdvController.advList', '/adv/advList' ,(select DISTINCT(ss.id)  from sys_menu as ss where ss.menu_code='product') ,1000,0,1,NULL,2);

INSERT INTO sys_menu (menu_name,menu_code,menu_url,pid,sort,is_delete,is_show,menu_summary,depth)
VALUES 
('添加广告', 'com.bandex.backend.web.BackendAdvController.createAdv', '/adv/createAdv' ,(select DISTINCT(ss.id)  from sys_menu as ss where ss.menu_code='com.bandex.backend.web.BackendAdvController.advList') ,1,0,0,NULL,3);

INSERT INTO sys_menu (menu_name,menu_code,menu_url,pid,sort,is_delete,is_show,menu_summary,depth)
VALUES 
('编辑广告', 'com.bandex.backend.web.BackendAdvController.editAdv', '/adv/editAdv' ,(select DISTINCT(ss.id)  from sys_menu as ss where ss.menu_code='com.bandex.backend.web.BackendAdvController.advList') ,1,0,0,NULL,3);

INSERT INTO sys_menu (menu_name,menu_code,menu_url,pid,sort,is_delete,is_show,menu_summary,depth)
VALUES 
('保存广告', 'com.bandex.backend.web.BackendAdvController.saveAdv', '/adv/saveAdv' ,(select DISTINCT(ss.id)  from sys_menu as ss where ss.menu_code='com.bandex.backend.web.BackendAdvController.advList') ,1,0,0,NULL,3);

INSERT INTO sys_menu (menu_name,menu_code,menu_url,pid,sort,is_delete,is_show,menu_summary,depth)
VALUES 
('更新状态', 'com.bandex.backend.web.BackendAdvController.changeStatus', '/adv/changeStatus' ,(select DISTINCT(ss.id)  from sys_menu as ss where ss.menu_code='com.bandex.backend.web.BackendAdvController.advList') ,1,0,0,NULL,3);

