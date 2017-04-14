<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%
String defaultApiServer = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<c:set var="ctx" value="${defaultApiServer}"/>
<!DOCTYPE html>
<html>
<head>
    <title>积分规则</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
</head>
<body style="margin:0px;">
    <div align="center">
    	<p class="MsoNormal" style="text-align:left;">
    		一、跃兑（<span>Bandex</span>）是一款通过日常运动获取积分，并且可使用积分获取相应会员等级、兑换奖励的应用<span>app</span>。<span></span>
    	</p>
    	<p class="MsoNormal" style="text-align:left;">
    		具体获取积分的途径和积分规则如下
    	</p>
    	<div align="center">
    		<table class="MsoNormalTable ke-zeroborder" border="0" cellpadding="0"  style="text-align:left;background:#CCCCCC;">
    			<tbody>
    				<tr>
    					<td  style="background:yellow;">
    						<p class="MsoNormal" align="center" style="text-align:center;white-space:nowrap;">
    							<b><span style="font-size:10.5pt;font-family:&quot;color:#333333;">积分</br>类型</span></b><span style="font-size:10.5pt;font-family:&quot;color:#333333;"></span>
    						</p>
    					</td>
    					<td  style="background:yellow;">
    						<p class="MsoNormal" align="center" style="text-align:center;white-space:nowrap;">
    							<b><span style="font-size:10.5pt;font-family:&quot;color:#333333;">每日积</br>分上限</span></b><span style="font-size:10.5pt;font-family:&quot;color:#333333;"></span>
    						</p>
    					</td>
    					<td  style="background:yellow;">
    						<p class="MsoNormal" align="center" style="text-align:center;">
    							<b><span style="font-size:10.5pt;font-family:&quot;color:#333333;">基本积分规则</span></b><span style="font-size:10.5pt;font-family:&quot;color:#333333;"></span>
    						</p>
    					</td>
    					<td  style="background:yellow;">
    						<p class="MsoNormal" align="center" style="text-align:center;">
    							<b><span style="font-size:10.5pt;font-family:&quot;color:#333333;">备注</span></b><span style="font-size:10.5pt;font-family:&quot;color:#333333;"></span>
    						</p>
    					</td>
    				</tr>
    				<tr>
    					<td  style="background:white;">
    						<p class="MsoNormal" align="center" style="text-align:center;">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">计步<span></span></span>
    						</p>
    					</td>
    					<td  style="background:white;">
    						<p class="MsoNormal" align="center" style="text-align:center;">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">100</span>
    						</p>
    					</td>
    					<td  style="background:white;">
    						<p class="MsoNormal">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">以<span>app</span>内置计步软件每日统计总步数：<span></span></span>
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">超过<span>10000</span>步，得<span>100</span>积分；<span></span></span>
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">超过<span>5000</span>步但不足<span>10000</span>步的，得<span>50</span>积分；<span></span></span>
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">不足<span>5000</span>步的，不得分。<span></span></span>
    						</p>
    					</td>
    					<td  style="background:white;">
    					</td>
    				</tr>
    				<tr>
    					<td  style="background:white;">
    						<p class="MsoNormal" align="center" style="text-align:center;">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">计时类运动<span></span></span>
    						</p>
    					</td>
    					<td  style="background:white;">
    						<p class="MsoNormal" align="center" style="text-align:center;">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">100</span>
    						</p>
    					</td>
    					<td  style="background:white;">
    						<p class="MsoNormal">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">用户从点击开始到结束，超过<span>1</span>小时的运动，即为有效运动，得<span>100</span>分，每天计时类的所有有效运动，计最高分值。<span></span></span>
    						</p>
    					</td>
    					<td  style="background:white;">
    						<p class="MsoNormal">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">运动项目包括：室内健身、瑜伽、私教、羽毛球、高尔夫、篮球、足球等等<span></span></span>
    						</p>
    					</td>
    				</tr>
    				<tr>
    					<td  style="background:white;">
    						<p class="MsoNormal" align="center" style="text-align:center;">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">计距离类运动<span>&nbsp;</span></span>
    						</p>
    					</td>
    					<td  style="background:white;">
    						<p class="MsoNormal" align="center" style="text-align:center;">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">100</span>
    						</p>
    					</td>
    					<td  style="background:white;">
    						<p class="MsoNormal">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">以用户开始点和结束点的路线<span>GPS</span>定位，跑步超过<span>5</span>公里，骑行超过<span>10</span>公里的，即为有效运动，得<span>100</span>分，每天计距离类的所有有效运动，计最高分值。<span></span></span>
    						</p>
    					</td>
    					<td  style="background:white;">
    						<p class="MsoNormal">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">运动项目包括：跑步和骑行<span></span></span>
    						</p>
    					</td>
    				</tr>
    				<tr>
    					<td  style="background:white;">
    						<p class="MsoNormal" align="center" style="text-align:center;">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">约跃<span></span></span>
    						</p>
    					</td>
    					<td  style="background:white;">
    						<p class="MsoNormal" align="center" style="text-align:center;">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">200</span>
    						</p>
    					</td>
    					<td  style="background:white;">
    						<p class="MsoNormal">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">当用户发起约跃或参加约跃（即与别人一起运动时），只要有除自己以外的至少<span>1</span>人同样以计时类运动和计距离运动的标准达标，奖励<span>100</span>分。每天在运动达标的基础上，每一类运动（计时类运动和计距离运动）奖励最多获取一次。<span></span></span>
    						</p>
    					</td>
    					<td  style="background:white;">
    						<p class="MsoNormal">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">&nbsp;</span>
    						</p>
    					</td>
    				</tr>
    				<tr>
    					<td  style="background:white;">
    						<p class="MsoNormal" align="center" style="text-align:center;">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">购买运动器材、户外活动<span></span></span>
    						</p>
    					</td>
    					<td  style="background:white;">
    						<p class="MsoNormal" align="center" style="text-align:center;">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">无<span></span></span>
    						</p>
    					</td>
    					<td  style="background:white;">
    						<p class="MsoNormal">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">以具体商品的维护信息为准。<span></span></span>
    						</p>
    					</td>
    					<td  style="background:white;">
    						<p class="MsoNormal">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">&nbsp;</span>
    						</p>
    					</td>
    				</tr>
    				<tr>
    					<td  style="background:white;">
    						<p class="MsoNormal" align="center" style="text-align:center;">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">成就<span></span></span>
    						</p>
    					</td>
    					<td  style="background:white;">
    						<p class="MsoNormal" align="center" style="text-align:center;">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">无<span></span></span>
    						</p>
    					</td>
    					<td  valign="bottom" style="background:white;">
    						<p class="MsoNormal">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">以具体达标成就的维护信息为准。<span></span></span>
    						</p>
    					</td>
    					<td  style="background:white;">
    						<p class="MsoNormal">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">&nbsp;</span>
    						</p>
    					</td>
    				</tr>
    			</tbody>
    		</table>
    	</div>
    	<p class="MsoNormal" style="text-align:left;">
    		<span>&nbsp;</span>
    	</p>
    	<p class="MsoNormal" style="text-align:left;">
    		二、会员等级：<span></span>
    	</p>
    	<div align="center">
    		<table class="MsoNormalTable ke-zeroborder" border="0" cellpadding="0"  style="text-align:left;background:#CCCCCC;">
    			<tbody>
    				<tr>
    					<td  style="background:yellow;">
    						<p class="MsoNormal" align="center" style="text-align:center;">
    							<b><span style="font-size:10.5pt;font-family:&quot;color:#333333;">会员等级</span></b><span style="font-size:10.5pt;font-family:&quot;color:#333333;"></span>
    						</p>
    					</td>
    					<td  style="background:yellow;">
    						<p class="MsoNormal" align="center" style="text-align:center;">
    							<b><span style="font-size:10.5pt;font-family:&quot;color:#333333;">每年累积积分</span></b><span style="font-size:10.5pt;font-family:&quot;color:#333333;"></span>
    						</p>
    					</td>
    					<td  style="background:yellow;">
    						<p class="MsoNormal" align="center" style="text-align:center;">
    							<b><span style="font-size:10.5pt;font-family:&quot;color:#333333;">等级福利</span></b><span style="font-size:10.5pt;font-family:&quot;color:#333333;"></span>
    						</p>
    					</td>
    				</tr>
    				<tr>
    					<td  style="background:white;">
    						<p class="MsoNormal" align="center" style="text-align:center;">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">普通会员<span></span></span>
    						</p>
    					</td>
    					<td  style="background:white;">
    						<p class="MsoNormal" align="center" style="text-align:center;">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">0~80000</span>
    						</p>
    					</td>
    					<td  style="background:white;">
    						<p class="MsoNormal" align="center" style="text-align:center;">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">优惠的运动器材和户外探险的折扣价格<span></span></span>
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">可用积分兑换相应的商品<span></span></span>
    						</p>
    					</td>
    				</tr>
    				<tr>
    					<td  style="background:white;">
    						<p class="MsoNormal" align="center" style="text-align:center;">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">金牌会员<span></span></span>
    						</p>
    					</td>
    					<td  style="background:white;">
    						<p class="MsoNormal" align="center" style="text-align:center;">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">80000</span><span style="font-size:10.5pt;font-family:&quot;color:#333333;">以上<span></span></span>
    						</p>
    					</td>
    					<td  style="background:white;">
    						<p class="MsoNormal" align="center" style="text-align:center;">
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">普通会员的福利<span>+</span></span>
    							<span style="font-size:10.5pt;font-family:&quot;color:#333333;">下一周年的癌症医疗费用全免保障<span></span></span>
    						</p>
    					</td>
    				</tr>
    			</tbody>
    		</table>
    	</div>
    	<p class="MsoNormal" style="text-align:left;">
    		<span>&nbsp;</span>
    	</p>
    	<div style="text-align:center;">
    	</div>
    </div>
</body>
</html>