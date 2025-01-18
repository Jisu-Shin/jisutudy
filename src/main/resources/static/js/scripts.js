var main = {
    init : function () {
        var _this = this;
        $('#btn-add').on('click', function () {
            _this.add();
        });

        $('#btn-save').on('click', function () {
            _this.saveCust();
        });

    },

    add : function () {
        var phonenumber = $('#ipt-phonenumber').val();
        if (oper.isEmpty(phonenumber)) {
            alert("전화번호를 입력해주세요.")

        } else {
            var insertTr = "";
            insertTr += "<tr><td>";
            insertTr += phonenumber;
            insertTr += "</td></tr>";
            $('tbody').append(insertTr);
            $('#ipt-phonenumber').val("");
        }

    },

    saveCust : function () {
        var data = {
            name: $('#name').val(),
            phoneNumber: $('#phonenumber').val(),
            smsConsentType: $('#smsConsentType').val()
        }
        oper.ajax("POST",data,'/api/v1/cust');
    }

};

var oper = {
    isEmpty : function (value) {
        if(value == "" || value == null || value == undefined) {
            return true;
        } else {
            return false;
        }
    },

    ajax : function (type, data, url) {
        $.ajax({
            'type': type,
            'url':url,
           'dataType':'json',
            'contentType':'application/json; charset=utf-8',
            'data':JSON.stringify(data)
        }).done(function() {
            alert("완료되었습니다.");
        });
    }
}

main.init();