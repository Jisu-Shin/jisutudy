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

        $('#btn-close').on('click', function() {
            _this.closeModal();
        });

        $('#btn-addPhoneNumber').on('click', function () {
            $('#div-addPhoneNumber').show();
            $('#div-choiceCust').hide();
        });

        $('#btn-choiceCust').on('click', function () {
            $('#div-addPhoneNumber').hide();
            $('#div-choiceCust').show();
//            console.log("고객 전체 조회 시작");
            oper.ajax('GET','','/api/custs',callback.choiceCust);
        });

        $('#btn-send').on('click', function () {
            console.log("체크된 고객 id");
            var checkbox = $("input[name=chb_cust]:checked");
            console.log(checkbox);

            _this.sendSms();
        });

        $('#phonenumber').on('input', function() {
            $(this).val($(this).val().replace(/[^0-9]/g, "").replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/\-{1,2}$/g, ""));
        })

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
            phoneNumber: $('#phonenumber').val().replace(/-/g,""),
            smsConsentType: $('#smsConsentType').val()
        }
        oper.ajax("POST",data,'/api/custs', callback.saveCust);
    },

    sendSms : function () {
        var checkbox = $("input[name=chb_cust]:checked");
//        checkbox.each({})
        var tr = checkbox.parent().parent();
        var td = tr.children();
//        console.log(td.eq(1).text());
//        console.log(td.eq(2).text());
        console.log(oper.getTodayDt());

        var data = {
                custId: td.eq(1).text(),
                smsContent: $('#content').val(),
                sendDt : oper.getTodayDt() ,
                smsType : "01"
            }
        oper.ajax("POST",data,'/api/sms/send', callback.sendSms);
    },

    closeModal : function() {
        window.location.href='/cust/findAll';
    }

};

var callback = {
    saveCust : function () {
        alert("고객 등록이 완료되었습니다");
        $("#frm-reset")[0].reset();
    } ,

    choiceCust : function (data) {
        console.log("고객 조회 완료");
        var tbody = $('#tbody');
        tbody.empty();
        data.forEach(cust => {
            var row = '<tr>'
            row+='<td><input type="checkbox" name="chb_cust" value="" </td>'
            row+='<td>'+cust.id+'</td>'
            row+='<td>'+cust.name+'</td>'
            row+='</tr>'
            tbody.append(row);
        });
    } ,

    sendSms : function () {
        alert("sms 발송이 완료되었습니다");
        window.location.href='/sms/send';
    }

}

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
    },

    getTodayDt : function() {
        let today = new Date();
        let year = String(today.getFullYear());
        let month = String(today.getMonth()+1).padStart(2,"0");
        let date = String(today.getDate()).padStart(2,"0");
        let hours = String(today.getHours()).padStart(2,"0");
        let minutes = String(today.getMinutes()).padStart(2,"0");
        return year+month+date+hours+minutes;
    }
}

main.init();