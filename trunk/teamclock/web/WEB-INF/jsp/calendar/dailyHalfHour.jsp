<%@ taglib uri="webwork" prefix="webwork" %>


<head>
<title>Calendar</title>
</head>
<body>

<table>
<tr>
<td class="bottom"><a href="<webwork:url value="'/calendarDaily.action'" >
	<webwork:param name="'targetDate'" value="targetDatePrevious" /></webwork:url>"><webwork:property value="targetDatePrevious" /></a>
</td>
<td class="bottom"><h1><webwork:property value="targetDate" /> </h1></td>
<td class="bottom"><a href="<webwork:url value="'/calendarDaily.action'" >
	<webwork:param name="'targetDate'" value="targetDateNext" /></webwork:url>"><webwork:property value="targetDateNext" /></a>
</td>
<td class="bottom"><a href="<webwork:url value="'/calendarWeekly.action'" >
	<webwork:param name="'targetDate'" value="targetDate" /></webwork:url>">Week</a>
</td>
<td class="bottom"><a href="<webwork:url value="'/calendarMonthly.action'" >
	<webwork:param name="'targetDate'" value="targetDate" /></webwork:url>">Month</a>
</td>
<td class="bottom">
<a href="<webwork:url value="'/calendarDaily.action'" >
	<webwork:param name="'targetDate'" value="actualDate" /></webwork:url>">(Today)</a>
</td>
<td class="bottom">
<a href="<webwork:url value="'/calendarmodify.action'" >
	<webwork:param name="'targetDate'" value="targetDate" /></webwork:url>">Add</a>
</td>
</tr>
</table>

<!--					
<webwork:form action="'calendarDaily.action'" method="'post'">



<table>
<tr>

<td><webwork:textfield theme = "'simple'"label="'Date'" name="'targetDate'" value="targetDate" />
<td><webwork:select theme = "'simple'"  label="'User'" name="'username'" value="username"
				list="users" listKey="username" listValue="username" emptyOption="true"  />
<td><webwork:submit  theme = "'simple'" value="'Show Schedule'" name="'showScheduleDailyHour'" />


</tr>
</table>

 </webwork:form>
 -->
 
 <table width="600" border="1">
		<webwork:iterator value="schedule.displayableBins" >

			    
				<tr> 
					<td width="100">	   <webwork:property value="binLower" />		</td>
		       
		            <td width="500">      <webwork:iterator value="displayCalendars"  >	


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
		       			
	                         </webwork:iterator> 
	                         &nbsp;
	                         </td>
	              </tr>

	       </webwork:iterator>
	</table>
	    
</body>
 
</html>