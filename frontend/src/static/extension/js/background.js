var token = "";
chrome.runtime.onMessage.addListener(
    
    function (request, sender, sendResponse) {
        console.log(request.data);
        $.ajax({
            url: request.url,
            method: request.method ? request.method : "GET",
            data: request.data ? JSON.stringify(request.data) : {},
            headers: {
                'Authorization':'Bearer '+ token,
            },
            contentType: "Application/JSON",
            crossDomain: true
        }).always(function (response) {
            if(request.url == "http://localhost:8085/api/authenticate") {
                if(response.id_token) {
                    token = response.id_token;
                    alert("Đăng nhập thành công")
                }
                else alert("Đăng nhập thất bại")
            }
            sendResponse(response);
        });
        return true;
    }
);
