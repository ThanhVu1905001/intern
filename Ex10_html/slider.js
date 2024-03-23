const imgPosition = document.querySelectorAll(".slide-container img")
    const imgContainer = document.querySelector(".slide-container")
    const dotItem = document.querySelectorAll(".dot")
    let imgNumber = imgPosition.length
    let index = 0 
    //console.log(imgPosition)
    imgPosition.forEach(function(image,index){
        image.style.left = index*100 + "%"
        dotItem[index].addEventListener("click",function(){
            silder(index)
        })
    })
    function imgSlide(){
        index ++;
        if(index>=imgNumber){index=0};
        silder(index);
    }
    function silder(index){
        imgContainer.style.left = "-" + index*100 +"%";
        const dotActive = document.querySelector(".active")
        dotActive.classList.remove("active")
        dotItem[index].classList.add("active")
    }
    setInterval(imgSlide,5000)