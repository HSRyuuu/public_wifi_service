function validateBookmarkGroupForm() {
    var name = document.getElementById("name").value;
    var priority = document.getElementById("priority").value;

    if (name.trim() == "") {
        alert("이름을 입력해주세요.");
        return false;
    }
    if (priority.trim() == "" || isNaN(priority)) {
        alert("순서를 숫자로 입력해 주세요.");
        return false;
    }
    return true;
}

function validateSelectBox() {
    var selectElement = document.getElementById("select");
    var selectedOption = selectElement.options[selectElement.selectedIndex];

    if (selectedOption.disabled) {
        alert("북마크 그룹을 선택하세요.");
        return false; // 폼 제출을 막기 위해 false를 반환
    }
    return true; // 유효성 검사를 통과한 경우 폼을 제출
}

