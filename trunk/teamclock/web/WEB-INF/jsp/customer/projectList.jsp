<%@ taglib uri="webwork" prefix="webwork" %>

 
<%@ page import="com.fivesticks.time.useradmin.FstxTimeSystemRights" %>

<html><head></head><body>

<h2>Project List</h2>
<a href="<webwork:url value="'/projectmodify.action'" />">Add+</a>
 

 
<div id="listTable" >
	<table>
	<tr>
		<th>Short Name</th>
		<th>Long Name</th>
		<th>Customer</th>
		<th>Active</th>
		<th>Postable</th>
	</tr>
		<webwork:iterator value="projects" >
			<tr>
				<td>
					<a href="<webwork:url value="'/projectmodify.action'" >
					<webwork:param name="'target'" value="project.id" /></webwork:url>">
					<webwork:property value="project.shortName" /></a>
				</td>				
				<td>
					<webwork:property value="project.longName" />
				</td>
				<td>
					<webwork:property value="customerName" />
				</td>
				<td>
					<webwork:property value="project.active" />
				</td>
					<td>
						<webwork:property value="postable" />
					</td>
			</tr>
		</webwork:iterator>
	</table>
</div>

</body></html>

