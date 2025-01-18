var main = {
    init : function () {
        var _this = this;
        $('#btn-add').on('click', function () {
            _this.add();
        });
    },

    add : function () {
        var phonenumber = $('#txt-phonenumber').val();
        if (main.isEmpty(phonenumber)) {
            alert("전화번호를 입력해주세요.")

        } else {
            var insertTr = "";
            insertTr += "<tr><td>";
            insertTr += phonenumber;
            insertTr += "</td></tr>";
            $('tbody').append(insertTr);
            $('#txt-phonenumber').val("");
        }

    },

    isEmpty : function (value) {
        if(value == "" || value == null || value == undefined) {
            return true;
        } else {
            return false;
        }
    }

};

main.init();