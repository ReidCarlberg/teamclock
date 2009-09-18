<%@ taglib uri="webwork" prefix="webwork" %>
 
<html>
<head>
<title>Pay Period Productivity Report</title>
</head>
<body>

<h1>Pay Period Productivity Report</h1>



	<webwork:form theme="'simple'" action="'payPeriodProductivityReport.action'" method="'post'">
		<table>

			<tr>
					<td>User: <webwork:select theme="'simple'" label="'User'" name="'user'" value="user"
					list="users" listKey="key" listValue="value" emptyOption="true"  />
				    
				  
			
				
				<td>Date: <webwork:textfield theme="'simple'" label="'Date'" name="'targetDate'" value="targetDate" />
				</td>
				
				<td>
					<webwork:submit theme="'simple'" value="'Search'" name="'buildReport'" />
					
				</td>
			</tr>
		</table>
	</webwork:form>
      
 <p>
 

   <table width="100%" border="1">
 <webwork:iterator value="report.days" status="dayStatus" >
 
 			<webwork:if test="#dayStatus.modulus(3) == 1">
				<webwork:set name="displayClass" value="'dashboardToDo_Alternate'"/>
				<!--herer-->
			</webwork:if>
			<webwork:else>
			<!--herer else-->
				<webwork:set name="displayClass" value="'dashboardToDo_Main'"/>			
			</webwork:else>
 
<tr>
		<webwork:if test="#dayStatus.count == 1">
			<tr><td>&nbsp</td>
			<webwork:iterator value="top.collection"  status="userStatus">
			<td colspan="3" class="dashboardToDo_Main" >
				<webwork:property value="user.username" />
			</td>
			</webwork:iterator>
			</tr>
			
			<tr><td>&nbsp</td>
			<webwork:iterator value="top.collection"  status="userStatus">
			<td class="dashboardToDo_Main" >Activity</td><td class="dashboardToDo_Main" >Time</td><td class="dashboardToDo_Main" >Productivity</td>
			</webwork:iterator>
			</tr>
		</webwork:if>

		<td class="<webwork:property value="#displayClass"/>"><webwork:property value="top.date" /></td> 
		<webwork:iterator value="top.collection"  status="userStatus">
		
	
				<td class="<webwork:property value="#displayClass"/>">
				<webwork:text name="'decimal.two.places.format'">
					<webwork:param value="activity"/>
				</webwork:text>
					&nbsp
				</td>
				<td class="<webwork:property value="#displayClass"/>">
					<webwork:property value="timeclock" />&nbsp
				</td>
				<td class="<webwork:property value="#displayClass"/>">
				<webwork:text name="'decimal.two.places.format'">
					<webwork:param value="productivity"/>
				</webwork:text>
				
				</td>
			     </webwork:iterator>	
			</tr>

		</webwork:iterator>
		 </table>
</body>
 
</html>