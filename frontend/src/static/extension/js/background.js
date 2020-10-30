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
                else {
                    alert("Đăng nhập thất bại")
                    token = "";
                }
                // if (!response.id_token) {
                //     if ($('.translated-ltr').length) {
                //         $(".ls-warning").css("display", "block")
                //     } else {
                //         $(".ls-warning").css("display", "none")
                //     }
                //     $("#errTbdnModal-order").tbdnModal("toggle");
                //     $(".ls-deposit p").text(""Đăng nhập thất bại");
                //     $("#tbdn-textarea").val("");

                // } else {
                //     if ($('.translated-ltr').length) {
                //         $(".ls-warning").css("display", "block")
                //     } else {
                //         $(".ls-warning").css("display", "none")
                //     }

                //     $("#myTbdnModal-order").tbdnModal("toggle");
                //     $(".ls-deposit p").text("Đăng nhập thành công");
                //     $("#tbdn-textarea").val("");

                    
                // }
            }
            sendResponse(response);
        });
        return true;
    }
);
