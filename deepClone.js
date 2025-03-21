function deepClone(obj){
    if(!obj||typeof obj != 'object'){
        return ;
    }
    let newObj = Array.isArray(obj)?[]:{};
    for(let key in obj){
        if(obj.hasOwnProperty(key)){
            newObj[key] = typeof obj[key] == 'object'?deepClone(obj[key]):obj[key];
        }
    }
    return newObj;
}

const obj = {
    a:1,
    b:2,
    c:3,
}

const newObj = deepClone(obj);
newObj.a = 5;
console.log(obj);
console.log(newObj);