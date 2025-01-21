var main = {
    init : function () {

        /* 초기화 섹션 */
        $('#div-choiceCust').hide();
        /* 끝 */

        var _this = this;
        $('#btn-add').on('click', function () {
            _this.add();
        });

        $('#btn-save').on('click', function () {
            _this.saveCust();
        });

        $('#btn-addPhoneNumber').on('click', function () {
            $('#div-addPhoneNumber').show();
            $('#div-choiceCust').hide();
        });

        $('#btn-choiceCust').on('click', function () {
            $('#div-addPhoneNumber').hide();
            $('#div-choiceCust').show();
            console.log("고객 조회 시작");
            oper.ajax('GET','','/custs',main.choiceCustCallback);
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
        oper.ajax("POST",data,'/api/v1/cust', main.saveCustCallback);
    },

    saveCustCallback : function () {
        alert("고객 등록이 완료되었습니다");
        window.location.href='/cust/save';
    } ,

    choiceCustCallback : function (data) {
        console.log("고객 조회 완료");
        var tbody = $('#tbody');
        tbody.empty();
        data.forEach(cust => {
            var row = '<tr>'
            row+='<td><input type="checkbox" value="" </td>'
            row+='<td>'+cust.name+'</td>'
            row+='<td>'+cust.phoneNumber+'</td>'
            row+='</tr>'
            tbody.append(row);
        });
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

    ajax : function (type, data, url, callback) {
        $.ajax({
            'type': type,
            'url':url,
           'dataType':'json',
            'contentType':'application/json; charset=utf-8',
            'data':JSON.stringify(data)
        }).done(function (response){
            callback(response);
        });
    }
}

main.init();