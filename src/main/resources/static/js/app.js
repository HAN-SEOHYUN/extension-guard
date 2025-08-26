$(function () {
    // '추가' 버튼 클릭 시
    $('#submitBtn').click(function () {
        var extensionName = $("#custom-extension-input").val();
        if (!check_text(extensionName)) {
            alert("올바른 확장자를 입력하세요 (20자 이내, 영문/숫자만 가능)");
            refresh();
            return false;
        }

        var data = {
            name: extensionName
        };

        $.ajax({
            cache: false,
            url: '/custom/save',
            type: 'POST',
            data: data,
            success: function (fragment) {
                // Assuming the server returns an updated HTML fragment
                $('#custom-list').replaceWith(fragment);
                refresh();
            },
            error: function (xhr, status, error) {
                $("#exist-alert-span").text("등록된 확장자이거나 200개를 초과했습니다.");
            }
        });
    });

    // 고정 확장자 체크박스 상태 변경 시
    $(document).on('change', '.fixed-checkbox', function () {
        var data = {
            name: $(this).val(),
            isChecked: $(this).is(":checked")
        };

        $.ajax({
            cache: false,
            // 올바른 URL로 수정: /api/fixed/update
            url: '/fixed/update',
            type: 'POST',
            data: data,
            success: function (fragment) {
                // Assuming the server returns an updated HTML fragment
                $('#fixed-list').replaceWith(fragment);
            },
            error: function (xhr, status, error) {
                console.error("고정 확장자 업데이트 실패", error);
            }
        });
    });

    // 커스텀 확장자 삭제 버튼 클릭 시
    $(document).on('click', '.delete-btn', function (event) {
        event.preventDefault();
        var nameToDelete = $(this).data('name');

        if (confirm('삭제하시겠습니까?')) {
            $.ajax({
                cache: false,
                url: '/custom/delete',
                type: 'POST',
                data: { name: nameToDelete },
                success: function (fragment) {
                    // Assuming the server returns an updated HTML fragment
                    $('#custom-list').replaceWith(fragment);
                },
                error: function (xhr, status, error) {
                    console.error("커스텀 확장자 삭제 실패", error);
                }
            });
        }
    });

    // 입력된 글자가 한글, 공백, 특수문자를 포함하면 false 리턴
    function check_text(extension) {
        const regex = /[^a-zA-Z0-9]/;
        return !(extension.length > 20 || regex.test(extension));
    }

    // 입력란 & 오류메시지 초기화
    function refresh() {
        $("#custom-extension-input").val("");
        $("#custom-extension-input").focus();
        $("#exist-alert-span").text("");
    }
});
