function validateBookmarkGroupForm(){
    var name = document.getElementById("name").value;
    var priority = document.getElementById("priority").value;

    if(name.trim() == ""){
        alert("이름을 입력해주세요.");
        return false;
    }
    if(priority.trim() == "" || isNaN(priority)){
        alert("순서를 숫자로 입력해 주세요.");
        return false;
    }
    return true;
}