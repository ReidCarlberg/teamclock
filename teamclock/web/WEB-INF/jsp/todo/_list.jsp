<%@ taglib uri="webwork" prefix="webwork" %>

<%@ taglib uri="fstxtime" prefix="time" %>



<div id="listTable" >
		<table width="100%"  >
			<tr>
				<th>Customer</th>
				<th>Project</th>
				<th>Assign To</th>
				<th>Detail</th>
				<th>Complete</th>
				<th>&nbsp;</th>
			</tr>
		
		<webwork:iterator value="todos">
			<tr>
				<td><webwork:property value="customerName"/>&nbsp;</td>
				<td><webwork:property value="projectName" />&nbsp;</td>

				<td><webwork:property value="assignedToUser" />&nbsp;</td>
				<td>
					<webwork:property value="detail" />&nbsp;
					<span class="additionalDetails">
						Created: <webwork:property value="createTimestamp" />
						<webwork:if test="target.modifyDate != null">
							Modified: <webwork:property value="target.modifyDate" />
							By: <webwork:property value="target.modifiedByUser" />
						</webwork:if>
						<webwork:if test="target.estimatedTotalHours">
							Est Total: <webwork:property value="target.estimatedTotalHours" />
						</webwork:if>
						<webwork:if test="target.estimatedRemainingHours">
							Est Remaining: <webwork:property value="target.estimatedRemainingHours" />
						</webwork:if>
						<webwork:if test="target.totalElapsedHours">
						Total elapsed: <webwork:property value="target.totalElapsedHours" />
						</webwork:if>

						<webwork:if test="target.totalChargeableElapsedHours">
						Total chargeable: <webwork:property value="target.totalChargeableElapsedHours" />
						</webwork:if>


					</span>
				</td>
				<td><webwork:property value="complete"/>&nbsp;</td>
				<td>
					<a href="<webwork:url value="'todomodify.action'" >
						<webwork:param name="'target'" value="id" />
					</webwork:url>"><img  class='jslink' src='images/icon-todo-more-hollow.gif' class='jslink' border='0' alt='more'  height='10px' width='10px' /></a>
				</td>
			</tr>
		</webwork:iterator>
		
		</table>
</div>		
