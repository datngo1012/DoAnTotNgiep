var cn1688 = function () {
    var self = this;
    self.first = true; //used for determined first run not getting product properties

    //get info from script on page (iDetailConfig and iDetailData)
    var scripts = document.querySelectorAll("script"); //get all scripts
    var content = {};
    for (var i = 0; i < scripts.length; i++) {
        if (scripts[i].text.includes("iDetailData")) {
            eval(scripts[i].text); //run the script contain keyword
            content = {
                config: iDetailConfig,
                data: iDetailData
            };
            break;
        }
    }

    /**
     * @desc Get the shop
     */
    self.getShop = function () {
        /**
         * Get shop id
         * @return {String} return shopId or empty string
         */
        function getShopId() {
            var shopId = content.config.userId;
            if (!shopId) {
                var json = $(rules.shop.id_data_mod_config).attr("data-mod-config");
                if (json) {
                    shopId = (JSON.parse(json)).userId;
                }
            }
            if (!shopId) {
                shopId = $(rules.info.cn1688.shop.id_data_seller_id).attr("data-seller-id");
            }
            return !shopId ? "" : shopId.trim();
        }

        /**
         * Get shop name
         * @return {String} return shopName or empty string
         */
        function getShopName() {
            var shopName;
            shopName = $(rules.info.cn1688.shop.name_json).attr('content');
            shopName = shopName.match(/name=(.+);/);
            if (shopName && shopName.length > 1) {
                shopName = shopName[1]
            } else {
                shopName = '';
            }
            if (!shopName) {
                shopName = $(rules.info.cn1688.shop.name_title).attr('title');
            }
            if (!shopName) {
                shopName = $(rules.info.cn1688.shop.name_text).text();
            }
            return (shopName) ? shopName : "";
        }

        /**
         * Get shop link
         * @return {String} return shopLink or empty string
         */
        function getShopLink() {
            var shopLink = $(rules.info.cn1688.shop.link_href).attr("href");
            if (!shopLink) {
                shopLink = $(rules.info.cn1688.shop.link_value).val();
            }
            return shopLink ? shopLink : "";
        }

        /**
         * Get shop aliwangwang chat name
         * @return {String} return aliwangwang or empty string
         */
        function getAliWangWang() {
            var aliwangwang;
            if (content.config.loginId) {
                aliwangwang = content.config.loginId;
            } else {
                aliwangwang = $(rules.info.cn1688.shop.aliwangwang_text).text();
                if (!aliwangwang) {
                    var json = $(rules.info.cn1688.shop.aliwangwang_json).attr("data-alitalk");
                    if (json) {
                        aliwangwang = decodeURI(JSON.parse(json).id);
                    }
                }
            }
            return aliwangwang ? aliwangwang : '';
        }

        // return the shop as an object
        return {
            "shopId": getShopId(),
            "shopName": getShopName(),
            "shopLink": getShopLink(),
            "aliwangwang": getAliWangWang(),
            "website": "CN1688",
            "version": rules.currentVersion
        };
    };

    /**
     *  Get the product from the page
     *  return {Array/Object} return 1 or more products
     **/
    self.getProduct = function () {
        var prod = this;

        /**
         * Get product id
         * @return {number} product id or 0
         */
        prod.getProductId = function () {
            var id = $(rules.info.cn1688.product.id_content).attr('content');
            if (!id) {
                id = $(rules.info.cn1688.product.id_value).val();
            }
            if (!id) {
                var path = window.location.pathname;
                id = path.match(/offer\/(.+)\.html/i);
                return (!id || id.length === 0) ? null : id[1];
            }
            return id ? id : 0;
        };

        /**
         * Get product price
         * 3 case: wholesales, wholesale discount, 1 price
         * @param product
         * @return {Object} return product with wholesSale or itemPriceNDT
         */
        prod.getProductPrice = function (product) {
            product.wholesales = [];
            var elem = $(rules.info.cn1688.product.priceWholesale);
            // 1st case wholesales on discount table
            if ($(elem).find(".price-discount-sku").length > 0) {
                var begin = /[\d]+/.exec($('#mod-detail-price .amount .value').text());
                var price = [];
                $(elem).find('.price-discount-sku .value').each(function () {
                    price.push($(this).text());
                });
                for (var i in price) {
                    if (price[i].indexOf(".") < (price[i].length - 3)) {
                        price[i] = price[i].replace(".", "");
                    }
                    if (price[i].indexOf(",")) {
                        price[i] = price[i].replace(",", ".");
                    }
                }
                if (price.length > 1) {
                    product.itemPrice = price;
                }
                product.wholesales.push({
                    "begin": parseInt(begin),
                    "end": 0,
                    "price": price[0]
                });
                // 1st case wholesales on normal table
            } else if ($(elem).find(".price-original-sku .value").length > 0) {
                var priceArr = [];
                $(elem).find(".price-original-sku .value").each(function (index, value) {
                    priceArr.push($(value).text());
                });
                var amountArr = [];
                $(rules.info.cn1688.product.amountWholesale).find(".value").each(function (index, value) {
                    var temp = /[\d]+/.exec($(value).text());
                    (temp && temp.length > 0) ? amountArr.push(temp[0]): null;
                });
                for (var i in priceArr) {
                    if (priceArr[i].indexOf(".") < (priceArr[i].length - 3)) {
                        priceArr[i] = priceArr[i].replace(".", "");
                    }
                    if (priceArr[i].indexOf(",")) {
                        priceArr[i] = priceArr[i].replace(",", ".");
                    }
                }
                product.itemPriceNDT = priceArr[0];
                if (amountArr.length < priceArr.length) {
                    product.itemPrice = priceArr;
                }
                for (var i in amountArr) {
                    product.wholesales.push({
                        begin: parseInt(amountArr[i]),
                        end: amountArr[i + 1] ? parseInt(amountArr[i + 1]) : 0,
                        price: priceArr[i]
                    });
                }
                // 2nd case 1 price on page
            } else {
                $(elem).find(".value").each(function (index, value) {
                    var range = $(value).parent().attr('data-range');
                    if (range) {
                        range = JSON.parse(range);
                        product.wholesales.push({
                            begin: range.begin,
                            end: range.end ? range.end : 0,
                            price: range.price
                        });
                    }
                });
            }
            // 3rd case wholesales discount
            if (!product.wholesales || !product.wholesales.length) {
                elem = [];
                $(rules.info.cn1688.product.promoPrice).each(function () {
                    elem.push($(this).text());
                });
                for (var i in elem) {
                    if (elem[i].indexOf(".") < (elem[i].length - 3)) {
                        elem[i] = elem[i].replace(".", "");
                    }
                    if (elem[i].indexOf(",")) {
                        elem[i] = elem[i].replace(",", ".");
                    }
                }
                product.wholesales = [{
                    begin: 1,
                    end: 0,
                    price: elem[0]
                }];
                if (elem.length > 1) {
                    product.itemPrice = elem;
                }
            }
            product.itemPriceNDT = product.wholesales[0].price;
            return product;
        };

        /**
         * Get product image
         * @return {String} return image or empty string
         */
        prod.getProductImages = function () {
            var image = $(rules.info.cn1688.product.image).attr("src");
            return image ? image : '';
        };

        /**
         * Get product title
         * @return {String} return title or empty string
         */
        prod.getProductTitle = function () {
            var title = $(rules.info.cn1688.product.title_content).attr('content');
            if (!title) {
                title = $(rules.info.cn1688.product.title_text).text();
            }
            return title ? title : '';
        };

        /**
         * Get location of product seller
         * @return {String} return address or empty string
         */
        prod.getSaleLocation = function () {
            var addr;
            $(rules.info.cn1688.product.saleLocation).each(function () {
                if (!addr && $(this).text()) {
                    addr = $(this).text();
                }
            });
            return addr ? addr : '';
        };

        /**
         * Get product properties
         * @param {Object} product
         * @returns {Object} return product with properties
         */
        prod.getProductProperties = function (product) {
            var properties = {
                type: '',
                name: '',
                images: ''
            };
            $('div.obj-leading').each(function () {
                if ($(this).find('span.obj-title .hidden').length > 0) { //.hidden is where original element name is stored when using translation
                    properties.type += $(this).find('span.obj-title .hidden').text() + ';';
                } else {
                    properties.type += $(this).find('span.obj-title').text() + ';';
                }
                $(this).find('.list-leading li').each(function () {
                    var elem = $(this).find('a.selected');
                    if (elem.length > 0) {
                        var img = elem.parent().attr('data-imgs');
                        var name = elem.parent().attr('data-unit-config');
                        if (img) {
                            properties.images += JSON.parse(img).preview + ';';
                        }
                        if (name) {
                            properties.name += JSON.parse(name).name + ';';
                        }
                    }
                });
            });
            if (properties) {
                product.propetiesType = properties.type.slice(0, -1);
                product.propetiesName = properties.name.slice(0, -1);
                product.propetiesImage = properties.images.slice(0, -1);
            }
            return product;
        };

        /**
         * Get user input quantity, price, stock, image, name, id of each sku
         * 3 cases:
         *      table: https://detail.1688.com/offer/543335124781.html
         *      hidden table: https://detail.1688.com/offer/41430889651.html
         *      1 price input: https://detail.1688.com/offer/1188748507.html
         * @param product
         * @returns {Array/Object}
         */
        prod.getInputQuantity = function (product) {
            // 1st case default table
            var skuElems = $('div.obj-sku');
            var products = [];
            if (skuElems && skuElems.length > 0) {
                if (product.propetiesType) {
                    product.propetiesType += ";";
                }
                if (skuElems.find('.obj-title .hidden').length > 0) {
                    product.propetiesType += skuElems.find('.obj-title .hidden').text();
                } else {
                    product.propetiesType += skuElems.find('.obj-title').text();
                }
                skuElems.find('table.table-sku tr').each(function () {
                    var dataSku = $(this).attr("data-sku-config");
                    var name, count;
                    if (dataSku) {
                        dataSku = JSON.parse(dataSku);
                        name = dataSku.skuName;
                        count = parseInt(dataSku.max);
                    }
                    if (!name) {
                        name = $(this).find('td.name').text().trim();
                        if (!name) {
                            name = $(this).find("td.name >span").attr("title").trim();
                        }
                        if (name) {
                            name = JSON.parse($(this).attr('data-sku-config')).skuName;
                        }
                    }
                    if (!count) {
                        count = $(this).find('td.count .value').text();
                        if (count.match(/[\s.,]+/)) {
                            count = count.replace(/[\s.,]+/, "");
                        }
                        if (count.includes("triệu")) {
                            count = count.match(/[\d]+/)[0] + "000000"
                        }
                    }
                    var image = $(this).find('span.image').attr('data-imgs');
                    var price = $(this).find('td.price .value').text();
                    if (price.indexOf(",")) {
                        price = price.replace(",", ".");
                    }
                    var amount = $(this).find('td.amount .amount-input').val();
                    var map = content.data.sku.skuMap;
                    var property = product.propetiesName;
                    if (name && price && count > 0 && amount > 0 && map) {
                        var p = $.extend({}, product);
                        if (p.propetiesName) {
                            p.propetiesName += ";";
                        }
                        p.propetiesName += name;
                        if (image) {
                            if (p.propetiesImage) {
                                p.propetiesImage += ";"
                            }
                            p.propetiesImage += (JSON.parse(image)).preview;
                        }
                        p.propetiesId = p.propetiesName;
                        var temp = (map[name] ? map[name] : map[property + "&gt;" + name]);
                        if (temp) {
                            p.skuId = temp.specId;
                            p.itemPriceNDT = temp.discountPrice ? temp.discountPrice : (temp.price ? temp.price : temp.retailPrice);
                        }
                        if (!p.itemPriceNDT || (p.wholesales.length > 1 && parseFloat(price) < parseFloat(p.itemPriceNDT))) {
                            p.itemPriceNDT = price;
                        }
                        p.stock = temp.canBookCount ? temp.canBookCount : count;
                        p.quantity = parseInt(amount);
                        products.push(p);
                    }
                });
                $.each(products, function (index, value) {
                    value.totalAmountNDT = prod.calculateTotal(value);
                });
                return (products.length > 0) ? products : product;
            } else {
                // 2nd case hidden table
                skuElems = $(".spu-list-content");
                if (skuElems && skuElems.length) {
                    if (product.propetiesType) {
                        product.propetiesType += ";";
                    }
                    product.propetiesType += $(".spu-content li.opt-li .d-header").text();
                    skuElems.find("#spu-opt-content table.static-table tr").each(function () {
                        var dataSku = $(this).attr("data-sku-config");
                        var name, price, count, amount, image;
                        if (dataSku) {
                            dataSku = JSON.parse(dataSku);
                            name = dataSku.skuName;
                            count = parseInt(dataSku.max);
                        }
                        if (!name) {
                            name = $(this).attr("data-sku-name");
                        }
                        if (!count) {
                            count = $(this).find("td.spu-stock .content-wrapper").text();
                            count = parseInt(count);
                        }
                        price = parseFloat($(this).find("td.spu-price").text().trim());
                        if ($(this).find("td.spu-amount .no-control").is(":visible")) {
                            amount = 0;
                        } else {
                            amount = $(this).find("td.spu-amount input.amount-input").val();
                        }
                        var map = content.data.sku.skuMap;
                        var property = product.propetiesName;
                        if (name && price && count > 0 && amount > 0 && map) {
                            var p = $.extend({}, product);
                            if (p.propetiesName) {
                                p.propetiesName += ";";
                            }
                            p.propetiesName += name;
                            if (image) {
                                if (p.propetiesImage) {
                                    p.propetiesImage += ";"
                                }
                                p.propetiesImage += (JSON.parse(image)).preview;
                            }
                            p.propetiesId = p.propetiesName;
                            var temp = (map[name] ? map[name] : map[property + "&gt;" + name]);
                            if (temp) {
                                p.skuId = temp.skuId;
                                p.itemPriceNDT = temp.discountPrice ? temp.discountPrice : (temp.price ? temp.price : temp.retailPrice);
                            }
                            if (!p.itemPriceNDT || (p.wholesales.length > 1 && parseFloat(price) < parseFloat(p.itemPriceNDT))) {
                                p.itemPriceNDT = price;
                            }
                            p.stock = temp.canBookCount ? temp.canBookCount : count;
                            p.quantity = parseInt(amount);
                            products.push(p);
                        }
                    });
                    $.each(products, function (index, value) {
                        value.totalAmountNDT = prod.calculateTotal(value);
                    });
                    return (products.length > 0) ? products : product;
                } else {
                    // 3rd case 1 price box
                    product.propetiesType += ";" + $("#J_DetailInside .obj-amount .d-title .hidden").text();
                    var inputElement = $("#J_DetailInside .mod-detail-purchasing input.amount-input");
                    if (inputElement.length > 0) {
                        var quantity = $(inputElement).val();
                    }
                    product.quantity = !quantity ? 1 : parseInt(quantity);
                    var stock = $(rules.info.cn1688.product.stock).text();
                    if (stock.includes("triệu")) {
                        stock = stock.match(/[\d]+/)[0] + "000000"
                    } else {
                        stock = stock.match(/[\d\s.,]+/)[0];
                        if (stock.match(/[\s.,]+/g)) {
                            stock = stock.replace(/[\s.,]/g, "");
                        }
                    }
                    product.stock = stock;
                    if (product.wholesales && product.wholesales.length) {
                        product.wholesales.forEach(function (cur) {
                            if (parseInt(cur.begin) <= parseInt(product.quantity) && (parseInt(cur.end) >= parseInt(product.quantity) || parseInt(cur.end) === 0)) {
                                product.itemPriceNDT = cur.price;
                            }
                        })
                    }
                    product.totalAmountNDT = prod.calculateTotal(product);
                    return product;
                }
            }
        };

        /**
         * Calculate cart total price in NDT
         * @param {Object} product
         * @returns {number}
         */
        prod.calculateTotal = function (product) {
            return Math.round((parseFloat(product.itemPriceNDT) * parseInt(product.quantity)) * 100) / 100;
        };

        /**
         * Update product price on box base on bold element
         */
        prod.updateView = function () {
            var highlight = $(".price .highlight-price");
            if (highlight && highlight.length) {
                var price = $(highlight).find('.value').text();
                if (price.indexOf(",")) {
                    price = price.replace(",", ".")
                }
                if (price) {
                    var priceVnd = tool.convertToVND(price) + "đ";
                    $('.tbdn-box-info #sell_price').text(priceVnd);
                }
            }
        };

        prod.init = function () {
            var product = {
                "itemId": prod.getProductId(),
                "itemName": prod.getProductTitle(),
                "itemImage": prod.getProductImages(),
                "saleLocation": prod.getSaleLocation(),
                "itemLink": tool.getProductLink()
            };
            product = prod.getProductPrice(product);
            if (product.wholesales.length === 1 && !product.itemPriceNDT) {
                product.itemPriceNDT = product.wholesales[0].price;
            }
            product.requireMin = parseInt(product.wholesales[0].begin);
            //check for first run
            if (!self.first) {
                product = prod.getProductProperties(product);
                product = prod.getInputQuantity(product);
                if (product.propetiesType && product.propetiesType.indexOf(";") === 0) {
                    product.propetiesType = product.propetiesType.slice(1);
                }
            } else {
                product.updateView = prod.updateView;
            }
            return product;
        };

        return prod.init();
    };

    /**
     * Display tbdn box on page
     * @param product
     */
    self.renderView = function (product) {
        var div = $('<div>');
        var html = addon.box;
        $(div).html(html);
        $(div).addClass('widget-custom offerdetail_ditto_purchasing');
        $(rules.info.cn1688.box_prepend).prepend($(div));
        // Get stock to display
        var totalAmount;
        if (content.data.sku && content.data.sku.canBookCount) {
            totalAmount = parseInt(content.data.sku.canBookCount);
        } else {
            $('.table-sku .count .value').each(function (index, value) {
                var text = $(value).text();
                if (text.match(/[\s.,]+/)) {
                    text = text.replace(/[\s.,]+/, "");
                }
                if (text.includes("triệu")) {
                    text = text.match(/[\d]+/)[0] + "000000"
                }
                totalAmount += parseInt(text);
            });
        }
        if (!totalAmount) {
            var text = $(rules.info.cn1688.product.stock).text();
            if (text.includes("triệu")) {
                text = text.match(/[\d]+/)[0] + "000000"
            }
            if (text) {
                var temp = text.match(/[\d\s.,]+/);
                if (temp && temp.length) {
                    temp = temp[0].replace(/[\s.,]+/g, "");
                }
                totalAmount = parseInt(temp);
            }
        }
        $('.tbdn-box-info #stock').text(totalAmount);
        //Display price
        if (product.itemPrice && Array.isArray(product.itemPrice) && product.itemPrice.length > product.wholesales.length) {
            var displayPrice = tool.convertToVND(product.itemPrice[0]) + "đ - " + tool.convertToVND(product.itemPrice[product.itemPrice.length - 1]) + "đ";
            $('.tbdn-box-info #sell_price').text(displayPrice);
        } else if (product.wholesales.length > 1) {
            var priceTable = "";
            $.each(product.wholesales, function (index, range) {
                priceTable += "<tr><td>Mua ≥ " + range.begin + " sản phẩm</td>" +
                    "<td class='text-tbdn'>" + tool.convertToVND(range.price) + "đ </td></tr>";
            });
            $(".tbdn-box-info .tbdn-1688-price").html(priceTable);
            $(".tbdn-box-info .tbdn-advanced-info").removeClass('hidden');
            $('.tbdn-box-info #sell_price').text(tool.convertToVND(product.itemPriceNDT) + "đ");
        } else {
            $('.tbdn-box-info #sell_price').text(tool.convertToVND(product.itemPriceNDT) + "đ");
        }
        // Display quantity condition
        if (product.requireMin > 1) {
            $(".tbdn-warning").text("Sản phẩm này yêu cầu số lượng mua ít nhất là: " + product.requireMin);
            $(".tbdn-warning").removeClass('hidden');
        }
    };

    /**
     * Get many products if product has selections
     * @returns {Array} return products
     */
    self.getProducts = function () {
        var products = [];
        //iterate through all selected to get product
        $('ul.list-leading .unit-detail-spec-operator a').each(function () {
            var prods;
            if ($(this).hasClass('selected')) {
                prods = self.getProduct();
            } else if ($(this).hasClass('completed')) {
                $(this).children().trigger('click');
                prods = self.getProduct();
            } else {
                return;
            }
            if (prods && prods.length) {
                if (!products.length) {
                    products = prods;
                } else {
                    prods.forEach(function (value) {
                        products.push(value);
                    });
                }
            }
        });
        return products.length ? products : self.getProduct();
    };

    /**
     * Surround the price box with red when user doesn't meet quantity condition
     */
    self.focusOnError = function () {
        $("div.obj-sku, li.opt-li, div.obj-amount").each(function () {
            $(this).find("input:first").focus();
            var style = {
                border: "1px solid red"
            };
            $("div.spu-list-content").css(style);
            $(this).css(style);
            // scroll to the error
            $('html, body').animate({
                scrollTop: $(this).offset().top
            }, 1000);
        });
    };

    self.init = function () {
        var product = self.getProduct();
        self.first = false;
        self.renderView(product);
    };
};