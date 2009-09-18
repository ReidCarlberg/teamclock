<%@ taglib uri="webwork" prefix="webwork" %>

 <webwork:iterator value="parentCollection" >
 <p>
 <div id="listTable" >
       <table  >
		<tr>
			<th >Date</th>
			<th >Start</th>
			<th >Stop</th>		
			<th >Username</th>
			<th >Project</th>
			<th >Elapsed</th>
			<th >Chargeable</th>			
			<th >Comments</th>
			<th>&nbsp;</th>
		</tr>
		
		<webwork:iterator value="child" >
		
			<tr>
				<td>
					 
					<webwork:property value="date" /> 				
				</td>
				<td>
					<webwork:property value="startSimple" />
				</td>
				<td>
					
				       <webwork:property value="stopSimple" />&nbsp;

				</td>			
				<td>
					<webwork:property value="username" />
				</td>
				<td>
					<webwork:property value="projectShort" />
				</td>
				<td>
					<webwork:property value="formattedElapsed" />&nbsp;
				</td>
				<td>
					<webwork:property value="formattedChargeableElapsed" />&nbsp;
				</td>				
				<td>
					<webwork:property value="comments" />&nbsp;
				</td>
				<td>
					<a href="<webwork:url value="'/activityrecordmodify.action'" >
					<webwork:param name="'target'" value="id" /></webwork:url>"><img  class='jslink' src='images/icon-todo-more-hollow.gif' class='jslink' border='0' alt='more'  height='10px' width='10px' /></a>
				</td>
			</tr>
			
			
			
             

	     
	     
	     </webwork:iterator>
	     
	     </table>
	      

	  <p>Total Elapsed:                 <webwork:property value="child.formattedTotalElapsed" />
	  
	  Total Chargeable:                 <webwork:property value="child.formattedTotalChargeableElapsed" /></p>
			
			</div>
	    </webwork:iterator>
		 
