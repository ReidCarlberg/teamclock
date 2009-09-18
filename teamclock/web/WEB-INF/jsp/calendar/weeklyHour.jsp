<%@ taglib uri="webwork" prefix="webwork" %>


<head>
<title>Weekly Calendar</title>
</head>
<body>

<table>
<tr>
<td class="bottom"><a href="<webwork:url value="'/calendarWeekly.action'" >
	<webwork:param name="'targetDate'" value="targetDatePrevious" /></webwork:url>"><webwork:property value="targetDatePrevious" /></a>
</td>
<td class="bottom"><h1>Week of <webwork:property value="targetDate" /> </h1></td>
<td class="bottom"><a href="<webwork:url value="'/calendarWeekly.action'" >
	<webwork:param name="'targetDate'" value="targetDateNext" /></webwork:url>"><webwork:property value="targetDateNext" /></a>
</td>
<td class="bottom"><a href="<webwork:url value="'/calendarWeekly.action'" >
	<webwork:param name="'targetDate'" value="actualDate" /></webwork:url>">(This Week)</a>
</td>
<td class="bottom"><a href="<webwork:url value="'/calendarMonthly.action'" >
	<webwork:param name="'targetDate'" value="targetDate" /></webwork:url>">Month</a></td>


</tr>
</table>

<p>
	



  
  
  	<table width="100%" border="1">
	  <tr>
		  <td width="9%">&nbsp;</td>
		 <webwork:iterator value="schedule.weeklySchedule.headers">
			<td width="13%">	
				<webwork:property value="dayOfWeek" />
				<a href="<webwork:url value="'/calendarDaily.action'" ><webwork:param name="'targetDate'" value="targetFormattedStartDate" /></webwork:url>"><webwork:property value="formattedStartDate" /></a>
				<a href="<webwork:url value="'/calendarmodify.action'" ><webwork:param name="'targetDate'" value="targetFormattedStartDate" /></webwork:url>">+</a>
			</td>
         </webwork:iterator>
	 </tr>
	
	 <webwork:iterator value="schedule.weeklySchedule.timeslots" >

		       <tr><td><webwork:property value="name" /></td>
				    
					 <webwork:iterator value="displayableBins" >  
						 <td><webwork:iterator value="displayCalendars"  >
							
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
		       			

							      
						   </webwork:iterator>&nbsp;</td> 
						 
					</webwork:iterator> 
				     
		      </tr>

       </webwork:iterator>
	
    </table>
	  	 
   
</body>
 
</html>