sendAjax = function(url, method, data, callBack) {
  chrome.runtime.sendMessage(
    {
      url: url,
      method: method,
      data: data,
    },
    callBack
  );
};

$(document).ready(function () {
  $("#mybutton").click(function () {
    var login = {
      username: $('#myform').find('input[name="username"]').val(),
      password: $('#myform').find('input[name="password"]').val()
    }
   
    sendAjax("http://localhost:8085/api/authenticate", "POST", login, function(resp) {
    });

  });

});