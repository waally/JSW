/**
 * Created with JetBrains WebStorm.
 * User: Administrator
 * Date: 14-3-21
 * Time: 下午3:38
 * To change this template use File | Settings | File Templates.
 */
(function($) {
    // plugin definition
    $.bvalidata = function(options) {
        var pass = true;
        $.each(timeouts,function(k,v){
            clearTimeout(v);
        });
        $.each(options,function(fieldname,rules){
            var fieldpass = true;
            $.each(rules,function(rule,message){
                if(fieldpass){
                    fieldpass=validatafield($("input[name='"+fieldname+"']"),rule,message);
                }
            });
            if(pass){
                pass=fieldpass;
            }
        });
        return pass;
    };

    function validatafield(field,rule,message){
        field.popover("destroy");
        if(!methods[rule](field,field.val())){
            field.popover({"content":message,"trigger":"manual"});
            field.popover("show");
//            timeouts.push(setTimeout(function() {field.popover("destroy"); }, 20000));
            return false;
        }else{
            return true;
        }
    }

    var timeouts = [];

    var methods= {
        not_empty: function(field, value) {
            return value !== null && $.trim(value).length > 0;
        },

        min_length: function(field, value) {
            min_len = field.attr("min_len");
            var length = $.trim(value).length
                ,result = (length >= min_len);
            return result;
        },

        max_length: function(field, value, max_len) {
            max_len =field.attr("max_len");
            return $.trim(value).length <= max_len;
        },

        regex: function(field, value, regexp) {
            regexp =eval(field.attr("regex"));
            return regexp.test(value);
        },

        email: function(field, value) {
            // by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
            var regex = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i;
            return regex.test($.trim(value));
        },

        url: function(field, value) {
            // by Scott Gonzalez: http://projects.scottsplayground.com/iri/
            var regex = /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i;
            return regex.test(value);
        },

        ip: function(field, value) {
            var regex = /^((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})$/i;
            return regex.test($.trim(value));
        },

        credit_card: function(field, value) {
            // accept only spaces, digits and dashes
            if (/[^0-9 \-]+/.test(value)) {
                return false;
            }
            var  nCheck = 0
                ,nDigit = 0
                ,bEven = false;

            value = value.replace(/\D/g, "");

            for (var n = value.length - 1; n >= 0; n--) {
                var cDigit = value.charAt(n);
                nDigit = parseInt(cDigit, 10);
                if (bEven) {
                    if ((nDigit *= 2) > 9) {
                        nDigit -= 9;
                    }
                }
                nCheck += nDigit;
                bEven = !bEven;
            }

            return (nCheck % 10) === 0;
        },

        alpha: function(field, value) {
            var regex = /^[a-z]*$/i;
            return regex.test(value);
        },

        alpha_numeric: function(field, value) {
            var regex = /^[a-z0-9]*$/i;
            return regex.test(value);
        },

        alpha_dash: function(field, value) {
            var regex = /^[a-z0-9_\-]*$/i;
            return regex.test(value);
        },

        digit: function(field, value) {
            var regex = /^\d*$/;
            return regex.test(value);
        },

        numeric: function(field, value) {
            var regex = /^([\+\-]?[0-9]+(\.[0-9]+)?)?$/;
            return regex.test(value);
        },

        // same as numeric
        decimal: function(field, value) {
            var regex = /^([\+\-]?[0-9]+(\.[0-9]+)?)?$/;
            return regex.test(value);
        }

    }
})(jQuery);

