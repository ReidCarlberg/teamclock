<%@ taglib uri="webwork" prefix="webwork" %>
<%@ taglib uri="fstxtime" prefix="time" %>
 
<html>
<head>
<title>Unposted Account Activity</title>
</head>
<body>

<h1>Unposted Account Activity</h1>

<p>This list only includes records with chargeable elapsed times greater than 0.
       
 <p>&nbsp;
 
 <div id="listTable" >	
 
 <webwork:form theme="'simple'" action="'/postUnpostedActivityList.action'" method="'post'">
       <table >
		<tr>
			<th>&nbsp;</th>
			<th>Username</th>
			<th>Project</th>
			<th>Task</th>
			<th>Date</th>
			<th>Elapsed</th>
			<th>Chargeable</th>
			<th>Skip</th>			
			<th>Comments</th>
			<%--<th>Actions</th>--%>
		</tr>
		
		<webwork:iterator value="unposted" status="mestatus">
		
			<tr>
				 <td>
					<a href="<webwork:url value="'/activityrecordmodify.action'" >
					<webwork:param name="'target'" value="id" /></webwork:url>">
					<webwork:property value="id" /> </a>
				</td>
				<td>
					<webwork:property value="username" />
				</td>
				<td>
					<webwork:property value="formattedProject" />
				</td>
				<td>
					<webwork:property value="formattedTask" />
				</td>
				<td>
					<webwork:property value="date" />
				</td>
				<td>
					<webwork:property value="formattedElapsed" />&nbsp;
				</td>
				<td>
					<webwork:hidden theme="'simple'" 
						name="'postList['+#mestatus.index+'].id'"
						value="id" />
					<webwork:textfield theme="'simple'" 
						name="'postList['+#mestatus.index+'].chargeableElapsed'"
						value="formattedChargeableElapsed" size="'8'" />
						
						</td>
						<td>
					<webwork:checkbox theme="'simple'" 
						name="'postList['+#mestatus.index+'].skip'"
						fieldValue="true" />
						
					<%--<webwork:property value="formattedChargeableElapsed" />&nbsp;--%>
				</td>				
				<td>
					<webwork:property value="formattedComments" />&nbsp;
				</td>
				<%--
				<td>
					<a href="<webwork:url value="'/accountActivityPost.action'" >
					<webwork:param name="'target'" value="id" /></webwork:url>">
					Post To Account</a>
				</td>
				--%>
			</tr>
			
			
	     </webwork:iterator>
	     
	     </table>
	      
	 <webwork:submit theme="'simple'" name="'submitChargable'" value="'Post Chargeable As Entered'" />
	 
	 </webwork:form>
	 
	 </div>  
	 <%--   
	      <p><a href="<webwork:url value="'/postUnpostedActivityList.action'" />">Post All Unposted Activity</a>--%>

	 

</body>
 
</html>