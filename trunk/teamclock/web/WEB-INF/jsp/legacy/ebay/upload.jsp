<%@ taglib uri="webwork" prefix="ww" %>

<html>
  <head>
   <title>File Upload </title>
  </head>
  <body>
    <h1>Image Upload</h1>


    <form action="auctionImageUpload.action" method="POST" enctype="multipart/form-data">


      <table width="350" border="0" cellpadding="3" cellspacing="0">
      <tr>
        <td colspan="2"><input type="file" name="picture" value="Browse..." size="100"/></td>
      </tr>
      <tr>
        <td colspan="2"><input type="file" name="picture1" value="Browse..." size="100"/></td>
      </tr>
      <tr>
        <td colspan="2"><input type="file" name="picture2" value="Browse..." size="100"/></td>
      </tr>
      <tr>
        <td colspan="2"><input type="file" name="picture3" value="Browse..." size="100"/></td>
      </tr>
      <tr>
        <td colspan="2"><input type="file" name="picture4" value="Browse..." size="100"/></td>
      </tr>
      <tr>
        <td colspan="2"><input type="file" name="picture5" value="Browse..." size="100"/></td>
      </tr>
      <tr>
        <td colspan="2"><input type="file" name="picture6" value="Browse..." size="100"/></td>
      </tr>
      <tr>
        <td colspan="2"><input type="file" name="picture7" value="Browse..." size="100"/></td>
      </tr>
      <tr>
        <td colspan="2"><input type="file" name="picture8" value="Browse..." size="100"/></td>
      </tr>
      <tr>
        <td colspan="2"><input type="file" name="picture9" value="Browse..." size="100"/></td>
      </tr>
      <tr>
        <td colspan="2" >
          <input type="submit" name="submitUpload" value="Submit">
        </td>
      </tr>
		</table>

  </form>

</body>
</html>