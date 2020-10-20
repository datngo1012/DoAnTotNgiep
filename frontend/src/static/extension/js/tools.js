/**
 * Commmon functions
 */
var tools = function () {
    var self = this;
    self.baseUrl = "http://localhost:8085/";
    var addUrl = "api/gio-hangs";
    var exchangeRateUrl = "api/v1/ExchangeRate";

    self.getCartUrl = "http://localhost:8081/backend/giohang";

    // var self = this;
    // self.baseUrl = "http://localhost:8085/";
    // var addUrl = "api/gio-hangs";
    // var saveProductUrl = "cart/saveLink";
    // var checkVersionUrl = "ext/version";

    // self.getCartUrl = "http://localhost:8081/backend/giohang";


    /**
     * Get name of the site
     * @return {string} return site name or rmpty string
     */
    self.getSiteName = function () {
        var url = window.location.href;
        if (url.match(/item.taobao/) || url.match(/taobao.com\/item\//)) {
            return "TAOBAO";
        }
        if (url.match(/detail.tmall/) || url.match(/tmall.com\/item\//)) {
            return "TMALL";
        }
        if (url.match(/detail.1688/) || url.match(/[d]+[e]+[t]+[a]+[i]+[l]+.1688/)) {
            return "cn1688";
        }
        return '';
    };

    /**
     * convert NDT price to VND
     * @param {string} price
     * @return {string} return converted price
     */
    self.convertToVND = function (price) {
        if (!$.isNumeric(price)) {
            if (price.indexOf("-") > 0) {
                var prices = price.split("-");
                price = "";
                for (var i in prices) {
                    price += " " + self.convertToVND(prices[i]) + " -";
                }
                price = price.slice(0, -1);
                return price;
            }
            return price;
        }
        price = parseFloat(price);
        var exchangeRate = rules.exchangeNum;
        var rounding = 1;
        var num = Math.ceil(price * exchangeRate / rounding) * rounding;
        return num.toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1.");
    };

    /**
     * Calculate exchange rate
     */
    self.calculateExchangeRate = function () {
        // self.sendAjax(exchangeRateUrl, "GET", null, function (response) {

        //     var result = JSON.parse(response);

        //     if (result && result.status) {
        //         rules.exchangeNum = result.data.exchange_rate;
        //         var rateStr = parseInt(result.data.exchange_rate).toLocaleString("vi");
        //         if (rateStr.indexOf(",") > 0) {
        //             rateStr = rateStr.replace(',', '.');
        //         }
        //         rules.exchangeRate = rateStr + "đ";
        //     } else {
        //         rules.exchangeNum = 3500;
        //         rules.exchangeRate = "3.500đ";
        //         console.warn("Không thể kết nối với server: DatHangTaoBaoDaNang.Com");
        //     }
        // });

        rules.exchangeNum = 3500;
                rules.exchangeRate = "3.500đ";
    };

    /**
     * Call background script to send ajax
     * @param {string} url
     * @param {string} method
     * @param {*} data
     * @param {function} callBack
     */
    self.sendAjax = function (url, method, data, callBack) {
        chrome.runtime.sendMessage({
            url: self.baseUrl + url,
            method: method,
            data: data
        }, callBack)
    };

    /**
     * Add the product to the cart using ajax
     * @param {Array} products
     * @param {object} shop
     */
    self.addToCart = function (product, shop) {
        $.each(product, function (index, value) {
            if (value.stock.toString().indexOf(".")) {
                value.stock = value.stock.toString().replace(".", "");
            }
            value.stock = self.convertNumber(value.stock);
            value.quantity = self.convertNumber(value.quantity);
            value.itemPriceNDT = self.convertNumber(value.itemPriceNDT);
        });

        var cart = {
            aliwangwang: shop.aliwangwang,
            shopId: shop.shopId,
            shopName: shop.shopName,
            shopLink: shop.shopLink,
            website: shop.website,
            items: product
        };

        self.sendAjax(addUrl, "POST", cart,
            function (response) {

                // var result = JSON.parse(response);
                var result = response;

                if (result && result.status) {
                    if ($('.translated-ltr').length) {
                        $(".ls-warning").css("display", "block")
                    } else {
                        $(".ls-warning").css("display", "none")
                    }

                    $("#myTbdnModal-order").tbdnModal("toggle");
                    var totalAmount = 0;
                    $.each(cart.items, function (index, value) {
                        totalAmount += value.totalAmountNDT;
                    });
                    totalAmount = Math.round(totalAmount * 100) / 100;
                    $(".ls-deposit p").text("Số tiền của sản phẩm phải trả: " + totalAmount + " NDT");
                    $("#tbdn-textarea").val("");

                } else {

                    if ($('.translated-ltr').length) {
                        $(".ls-warning").css("display", "block")
                    } else {
                        $(".ls-warning").css("display", "none")
                    }

                    if (result.data == "login") {
                        $('#btnLogin').css("display", "inline");
                    } else {
                        $('#btnLogin').css("display", "none");
                    }

                    $("#errTbdnModal-order").tbdnModal("toggle");
                    $(".ls-deposit p").text(result.error);
                    $("#tbdn-textarea").val("");
                }
            }
        );
    };


    /**
     * convert String no Number
     * @params {string} str
     * @params {number}
     */
    self.convertNumber = function (str) {
        if (!str || $.isNumeric(str)) {
            return str;
        }
        if (str.toString().indexOf(',') > 0) {
            str = str.toString().replace(/[,]+/, ".");
        }

        return parseFloat(str);
    };

    /**
     * Go to page
     * @param {string} url
     */
    self.goToSite = function (url) {
        window.location.replace(self.baseUrl + url);
    };

    /**
     * wrapper for window.location.href
     * @return {string} href
     */
    self.getProductLink = function () {
        return window.location.href;
    };
};

var tool = new tools();