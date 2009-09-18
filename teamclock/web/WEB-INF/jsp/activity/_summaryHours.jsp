<%@ taglib uri="webwork" prefix="webwork" %>

	<p>
Total Shift Time (hrs): <webwork:property value="totalShiftHoursFormatted" />
Total Break Time (hrs): <webwork:property value="totalShiftHoursBreakFormatted" /></p>

	<p>Note: Percent of Shift (Elaspsed) is based on total shift hours less total break hours 
	(<webwork:property value="totalShiftHoursMinusBreakFormatted" />).</p>
	
