<%@ taglib uri="webwork" prefix="webwork" %>

<div id="calendarMonthly">
<table border="1" cellspacing="1" cellpadding="4" width="100%">
	<tr>
	<th width="2%">W</th>
	<th width="14%">Sunday</th>
	<th width="14%">Monday</th>
	<th width="14%">Tuesday</th>
	<th width="14%">Wednesday</th>
	<th width="14%">Thursday</th>
	<th width="14%">Friday</th>
	<th width="14%">Saturday</th>
	</tr>
	 
	<webwork:iterator value="schedule.displayableBins" status="tr">
	    <webwork:if test="#tr.index %7==0">
	    <tr>
	    <td>
	    <a href="<webwork:url value="'/calendarWeekly.action'" >
					                     <webwork:param name="'targetDate'" value="formattedDate" /></webwork:url>">W</a>
	    </td>
	    </webwork:if>
			<webwork:if test="today==true" >
			     <td  class="today" >
			</webwork:if>
			<webwork:else >
			    <td >
			</webwork:else>
			
			<table width="100%" border="0">
				
					<tr>
				
					<td valign="top" align="right">
					     
					                     
					     <table width="100%">
					     <tr><td width="100%" align="right" >
					     		<a href="<webwork:url value="'/calendarDaily.action'" >
					                     <webwork:param name="'targetDate'" value="formattedDate" /></webwork:url>">
							     <webwork:property value="dayOfMonth" /></a>
						      <a href="<webwork:url value="'/calendarmodify.action'" >
					                     <webwork:param name="'targetDate'" value="formattedDate" /></webwork:url>">+</a>
							   
				            </td></tr>
				              <webwork:iterator value="displayCalendars"  >
						<tr><td width="100%" align="left" >

	 						<webwork:if test="!calendar.username.equals(sessionContext.user.username) && sessionContext.userTypeEnum.name.equals(\"USERTYPE_STANDARD\")">
				       			<webwork:property value="calendar.username" />
				       			<webwork:property value="calendar.description" />
			       			</webwork:if>
			       			<webwork:else>
								<a href="<webwork:url value="'/calendarmodify.action'" >
								<webwork:param name="'target'" value="calendar.id" /></webwork:url>">
								<webwork:property value="calendar.username" />
								<webwork:property value="calendar.description" /></a>	 	            
			       			</webwork:else>
							      
						</tr></td>	 
				    	     </webwork:iterator></table>
					</td>
				 
					</tr>
				 
			</table>
			</td>
	    <webwork:if test="#tr.index %7==7">
	      </tr>
	    </webwork:if>	
	</webwork:iterator>
		
	 
	
	
	
</table>
</div>