var taobao = function () {
    var self = this;
    //check for first time run
    self.first = true;

    /**
     * Get shop information
     * @return {Object} shop
     */
    self.getShop = function () {

        /**
         * Get shop id
         * @return {String} return shopId or empty string
         */
        function getShopId() {
            var seller_id = $(rules.info.TAOBAO.shop.id_data_api).attr("data-api");
            if (seller_id) {
                var regex = /\&seller_num_id=(.+)\&totalSQ/i;
                seller_id = regex.exec(seller_id);
                seller_id = (!seller_id || seller_id.length <= 1) ? "" : seller_id[1];
            } else {
                seller_id = $(rules.info.TAOBAO.shop.id_data_sellerid).attr("data-sellerid")
            }
            return seller_id ? seller_id : '';
        }

        /**
         * Get shop name
         * @return {String} return shopName or empty string
         */
        function getShopName() {
            var name = $(rules.info.TAOBAO.shop.name_title).attr("title");
            if (!name) {
                name = $(rules.info.TAOBAO.shop.name_text).text();
            }
            return name ? name : '';
        }

        /**
         * Get shop link
         * @return {String} return shopLink or empty string
         */
        function getShoplink() {
            var link = $(rules.info.TAOBAO.shop.link).attr("href");
            return link ? 'https:' + link : '';
        }

        /**
         * Get shop aliwangwang chat
         * @return {String} return aliwangwang or empty string
         */
        function getAliWangWang() {
            var aliwangwang = $(rules.info.TAOBAO.shop.aliwangwang).attr("data-nick");
            if (!aliwangwang) {
                aliwangwang = $(rules.info.TAOBAO.shop.aliwangwang).attr("data-tnick");
            }
            if (aliwangwang && aliwangwang.indexOf("%") >= 0) {
                aliwangwang = decodeURIComponent(aliwangwang);
            }
            return aliwangwang ? aliwangwang : '';
        }

        return {
            "shopId": getShopId(),
            "shopName": getShopName(),
            "shopLink": getShoplink(),
            "aliwangwang": getAliWangWang(),
            "website": "TAOBAO"
        }
    };

    /**
     * Get product from page
     * @return {Object} return product
     **/
    self.getProduct = function () {
        var prod = this;

        /**
         * Get product id
         * @return {number} return productId or 0
         */
        prod.getProductId = function () {
            var id = $(rules.info.TAOBAO.product.id).val();
            if (!id) {
                var path = window.location.href;
                id = path.match(/item\/(.+)\.htm/i);
                if (!id || !id.length) {
                    id = path.match(/id=(.+)&/);
                }
                id = id && id.length > 1 ? id[1] : 0;
            }
            return id ? id : 0;
        };

        /**
         * Get product price
         * @return {String/0} return price or 0
         */
        prod.getProductPrice = function () {
            var price = $(rules.info.TAOBAO.product.promoPrice).text().match(/[0-9,.]+/g);
            if (!price || !price.length) {
                price = $(rules.info.TAOBAO.product.basePrice).text().match(/[0-9,.]+/g);
            }
            for (var i in price) {
                if (price[i].indexOf(".") < (price[i].length - 3)) {
                    price[i] = price[i].replace(".", "");
                }
                if (price[i].indexOf(",")) {
                    price[i] = price[i].replace(",", ".");
                }
            }
            return price && price.length > 1 ? price : (price && price.length ? price[0] : 0);
        };

        /**
         * Get product image
         * @return {string} return image or empty string
         */
        prod.getProductImages = function () {
            var image = $(rules.info.TAOBAO.product.image).attr('src').replace(/[\d]+x[\d]+/, "500x500");
            return image ? (image.indexOf('http') >= 0 ? image : 'https:' + image) : '';
        };

        /**
         * Get product title
         * @return {string} return title or empty string
         */
        prod.getProductTitle = function () {
            var name = $(rules.info.TAOBAO.product.title_content).attr('content');
            if (!name) {
                name = $(rules.info.TAOBAO.product.title_data_title).attr("data-title");
            }
            return name ? name : '';
        };

        /**
         * Get sale location
         * @return {string} return location or empty string
         */
        prod.getSaleLocation = function () {
            var location = $(rules.info.TAOBAO.product.saleLocation).text();
            return location ? location : ''
        };

        /**
         * Update the price on box if price on page change
         */
        prod.updateView = function () {
            var price = prod.getProductPrice();
            var priceVnd = '';
            if (Array.isArray(price) && price.length > 1) {
                priceVnd = tool.convertToVND(price[0]) + "đ - " + tool.convertToVND(price[1]) + "đ";
            } else {
                priceVnd = tool.convertToVND(price) + "đ";
            }
            $('.chipo-box-info #sell_price').text(priceVnd);

            var stock = prod.getSelectStock();
            $('.chipo-box-info #stock').text(stock);
        };

        /**
         * get product quantity
         * @return {number} return quantity
         */
        prod.getInputQuantity = function () {
            var quantity = $(rules.info.TAOBAO.product.quantity).val();
            return !quantity ? 1 : quantity;
        };

        /**
         * get product stock based on properties chosen
         * @return {number} return stock or 0 if out of stock, 10 if can't get
         */
        prod.getSelectStock = function () {
            var stock = /[\d]+/.exec($(rules.info.TAOBAO.product.stock).text());
            if (stock && stock.length) {
                return stock[0];
            } else if ($('.tb-stop').is(':visible') || $(".J_TOffSale").is(":visible")) {
                return 0;
            }
            return 10;
        };

        /**
         * get product properties
         * @param {object} product
         * @return {Object} return product with properties
         */
        prod.getProductProperties = function (product) {
            //get props type
            var propertiesType = '';
            if ($("#J_SKU dt .hidden").length > 0) {
                $('#J_SKU dt .hidden').each(function () {
                    propertiesType += $(this).text() + ';';
                });
            } else if ($("#J_SKU dt ").length > 0) {
                $('#J_SKU dt').each(function () {
                    propertiesType += $(this).text() + ';';
                });
            } else if ($("#J_isku .J_Prop .tb-property-type .hidden").length > 0) {
                $("#J_isku .J_Prop .tb-property-type .hidden").each(function () {
                    propertiesType += $(this).text() + ';';
                });
            } else {
                $("#J_isku .J_Prop .tb-property-type").each(function () {
                    propertiesType += $(this).text() + ';';
                });
            }
            //get props html
            var props = $('.J_TSaleProp');
            if (!props || props.length <= 0) {
                props = $('#J_SKU dl');
            }
            //get selected props
            var propsSelected = $(props).find('li.tb-selected');
            if (props.length > propsSelected.length) {
                //add red border to properties that not chosen and scroll to the first properties not chosen
                $(props).find("li").each(function (index) {
                    if (!$(this).hasClass("tb-selected")) {
                        var e = $(this).parents(".J_Prop");
                        e.css("border", "1px solid red");
                        $(this).click(function () {
                            e.css("border", "none");
                        });
                        if (index === 0) {
                            $('html, body').animate({
                                scrollTop: e.offset().top
                            }, 1000);
                        }
                    }
                });
                alert("Xin chọn đủ thuộc tính của sản phẩm");
                return false;
            } else {
                //get props id, name, images
                var propertiesId = '';
                var propertiesName = '';
                var propertiesImages = '';
                for (var i = 0; i < propsSelected.length; i++) {
                    if ($(propsSelected[i]).attr('data-pv')) {
                        propertiesId += $(propsSelected[i]).attr('data-pv') + ';';
                    } else {
                        propertiesId += $(propsSelected[i]).attr("data-value") + ";";
                    }
                    var elem = $(propsSelected[i]).find("a");
                    if (elem.hasClass("tb-img") || elem.attr("style")) {
                        var imgUrl = elem.attr('style').match(/\/\/.+\..+\.[a-z]{3,4}/g);
                        propertiesImages += "https:" + imgUrl + ';';
                    }
                    if (elem.attr("title")) {
                        propertiesName += elem.attr('title') + ';';
                    } else {
                        propertiesName += elem.find("span").text() + ";";
                    }
                }
                //remove left over ";"
                product.propetiesType = propertiesType.slice(0, -1);
                product.propetiesId = propertiesId.slice(0, -1);
                product.propetiesName = propertiesName.slice(0, -1);
                product.propetiesImage = propertiesImages.slice(0, -1);
                return product;
            }
        };

        /**
         * calculate total price of product
         * @param {object} product
         * @return {object} return product
         */
        prod.calculateTotal = function (product) {
            product.totalAmountNDT = Math.round((parseInt(product.quantity) * parseFloat(product.itemPriceNDT)) * 100) / 100;
            return product;
        };

        prod.init = function () {
            var product = {
                "itemId": prod.getProductId(),
                "itemName": prod.getProductTitle(),
                "itemLink": tool.getProductLink(),
                "itemImage": prod.getProductImages(),
                "saleLocation": prod.getSaleLocation(),
                "itemPriceNDT": prod.getProductPrice(),
                "stock": prod.getSelectStock()
            };
            // if there are 2 price turn itemPrice from array to string
            if (product.itemPriceNDT && product.itemPriceNDT.length > 1) {
                product.itemPrice = "từ " + tool.convertToVND(product.itemPriceNDT[0]) + " đ đến " + tool.convertToVND(product.itemPriceNDT[1]);
            }
            // check first run
            if (!self.first) {
                product = prod.getProductProperties(product);
                product.quantity = prod.getInputQuantity();
                product = prod.calculateTotal(product);
            } else {
                product.updateView = prod.updateView;
            }
            return product;
        };

        return prod.init();
    };


    /**
     * add chipo box to page
     * @param {object} product
     */
    self.renderView = function (product) {
        var div = $('<div>');
        var html = addon.box;
        $(div).html(html);
        $(div).addClass('tm-clear');
        $(rules.info.TAOBAO.box_prepend).prepend($(div));
        // if ($(rules.info.TAOBAO.product.hiddenPrice).length) {
        //     $('.chipo-warning').removeClass('hidden');
        // }
        $('.chipo-box-info #stock').text(product.stock);
        var priceVnd = '';
        if (Array.isArray(product.itemPriceNDT) && product.itemPriceNDT.length > 1) {
            priceVnd = tool.convertToVND(product.itemPriceNDT[0]) + "đ - " + tool.convertToVND(product.itemPriceNDT[1]) + "đ";
        }
        else {
            priceVnd = tool.convertToVND(product.itemPriceNDT) + "đ";
        }
        $('.chipo-box-info #sell_price').text(priceVnd);
    };

    self.init = function () {
        var product = self.getProduct();
        self.udpateView = product.updateView;
        self.first = false;
        self.renderView(product);
    };
};
