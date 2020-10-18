$(function() {
  // Send a message to the active tab
  chrome.tabs.query({ active: true, currentWindow: true }, function(tabs) {
    var activeTab = tabs[0];
    chrome.tabs.sendMessage(activeTab.id, { message: "getFirstFormId" });
  });
});

chrome.runtime.onMessage.addListener(function(request, sender, sendResponse) {
  // if (request.message === "find_first_form") {
  //     $('#formId').val("#" + request.formId);
  // }
  $(document).ready(function() {
    $("#myform").submit(function() {
      console.log("helloi");
    });
  });
});
