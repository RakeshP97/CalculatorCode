<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Calculator</title>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript">
function onClick(value){
	console.log('Enter value is '+value);

	var firstNumber = 0;
	var SecondNumber = 0;
	var operator="ADD";
	if(value.indexOf('+')>0)
		{
	     firstNumber = value.substring(0, value.indexOf('+'));
	     SecondNumber = value.substring(value.indexOf('+')+1);
	     operator="ADD";
		}
	   else if (value.indexOf('-')>0)
		{
		     firstNumber = value.substring(0, value.indexOf('-'));
		     SecondNumber = value.substring(value.indexOf('-')+1);
		     operator="SUBSTRACT";
		}
		 else if (value.indexOf('*')>0)
			{
			     firstNumber = value.substring(0, value.indexOf('*'));
			     SecondNumber = value.substring(value.indexOf('*')+1);
			     operator="MULTIPLY";
			}
			 else if (value.indexOf('/')>0)
				{
				     firstNumber = value.substring(0, value.indexOf('/'));
				     SecondNumber = value.substring(value.indexOf('/')+1);
				     operator="DIVIDE";
				}
			 else if (value.indexOf('@')>=0)
				{
				     firstNumber = value.substring(value.indexOf('@')+1);
				     operator="SQRT";
				}
    console.log('Entered value is firstNumber '+firstNumber +':::'+SecondNumber+':::'+operator);
	jQuery.ajax({
        type: "PUT",
        url : "http://localhost:8081/TestCalc/rest/calculate",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({"firstNumber":firstNumber,"secondNumber":SecondNumber,"operator":operator}),
        timeout: 15000,
        success: function (response, textStatus, xhr) {
            console.log('success: '+response.value);  
            $('#display').val(response.value);
        },
        error: function (e) {
            console.log('error: ', e);

        },
        done: function (e) {
            console.log('done');
        }

    })
}
function perviousValue(){

	jQuery.ajax({
        type: "GET",
        url : 'http://localhost:8081/TestCalc/rest/getPreviousResult',
        timeout: 15000,
        success: function (response) {
            console.log('Previous calculated value is : '+response.value);  
            $('#display').val(response.value);
        },
        error: function (e) {
            console.log('error: ', e);

        },
        done: function (e) {
            console.log('done');
        }

    })
}
function clearMemory(){
	jQuery.ajax({
		type:"GET",
		url:"http://localhost:8081/TestCalc/rest/clearMemory",
		timeout: 15000,
		success:function(response){
			console.log('successfully cleared');
			$('#display').val('');
		},
        error: function (e) {
            console.log('error: ', e);

        },
        done: function (e) {
            console.log('done');
        }
	})
}

 jQuery(document).ready(function(){
            $('#display').keypress(function() {
                if ($('#display').val().length < 10) {
                    $('#display').val($('#display').val().substring(0,9));
                }
                
            }) 

        });
</script>
</head>
<body>
	     <form name="calculator">
         <table>
            <tr>
               <td colspan="4">
                  <input type="text" name="display" id="display" maxlength="8" max="999999999" readonly="readonly">
               </td>
            </tr>
            <tr>
               <td><input type="button" name="one" value="1" onclick="calculator.display.value += '1'"></td>
               <td><input type="button" name="two" value="2" onclick="calculator.display.value += '2'"></td>
               <td><input type="button" name="three" value="3" onclick="calculator.display.value += '3'"></td>
               <td><input type="button" class="operator" name="plus" value="+" onclick="calculator.display.value += '+'"></td>
            </tr>
            <tr>
               <td><input type="button" name="four" value="4" onclick="calculator.display.value += '4'"></td>
               <td><input type="button" name="five" value="5" onclick="calculator.display.value += '5'"></td>
               <td><input type="button" name="six" value="6" onclick="calculator.display.value += '6'"></td>
               <td><input type="button" class="operator" name="minus" value="-" onclick="calculator.display.value += '-'"></td>
            </tr>
            <tr>
               <td><input type="button" name="seven" value="7" onclick="calculator.display.value += '7'"></td>
               <td><input type="button" name="eight" value="8" onclick="calculator.display.value += '8'"></td>
               <td><input type="button" name="nine" value="9" onclick="calculator.display.value += '9'"></td>
               <td><input type="button" class="operator" name="times" value="x" onclick="calculator.display.value += '*'"></td>
            </tr>
            <tr>
               <td><input type="button" id="clear" name="clear" value="c" onclick="calculator.display.value = clearMemory()"></td>
               <td><input type="button" name="zero" value="0" onclick="calculator.display.value += '0'"></td>
               <td><input type="button" name="doit" value="=" onclick="calculator.display.value = onClick(calculator.display.value)"></td>
               <td><input type="button" class="operator" name="div" value="/" onclick="calculator.display.value += '/'"></td>
            </tr>
             <tr>
               <td><input type="button" id="previous" name="previous" value="prev" onclick="perviousValue()"></td>
               <td><input type="button" name="Sqrt" value='Sqrt' value='@' onclick="calculator.display.value += '@' "></td>
               <td><input type="button" name="PI" value="PI" onclick="calculator.display.value += Math.PI"></td>
               <td><input type="button" class="operator" name="decimal" value="." onclick="calculator.display.value += '.'">
            </tr>
         </table>
      </form>
</body>
</html>
