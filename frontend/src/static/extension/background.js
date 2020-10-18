chrome.runtime.onMessage.addListener(
    function (request, sender, sendResponse) {
        $.ajax({
            url: request.url,
            method: request.method ? request.method : "GET",
            data: request.data ? JSON.stringify(request.data) : {},
            contentType: "Application/JSON",
            crossDomain: true
        }).always(function (response) {
            sendResponse(response);
        });
        return true;
    }
);
