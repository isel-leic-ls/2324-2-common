window.addEventListener('load', loadHandler)
window.addEventListener('hashchange', hashChangeHandler)

// For more information on DOM Events, see https://developer.mozilla.org/en-US/docs/Learn/JavaScript/Building_blocks/Events
// For reference on the used API see:
// 1 - addEventListener on the window object: https://www.w3schools.com/jsref/met_win_addeventlistener.asp
// 2 - addEventListener on the element object: https://www.w3schools.com/jsref/met_element_addeventlistener.asp
// 3 - List of DOM Events: https://www.w3schools.com/jsref/dom_obj_event.asp

function loadHandler(){
    hashChangeHandler()
}

function hashChangeHandler(){

    const mainContent = document.getElementById("mainContent")

    switch(window.location.hash){
        case '#home' : return home()
        case '#students' : return students()
        case '#students/10' : return studentDetails()
        default : return defaultRoute()
    }

    // Routes handler functions

    function home() {
        const h1 = document.createElement("h1")
        const text = document.createTextNode("Home");
        h1.appendChild(text)
        mainContent.replaceChildren(h1)
    }

    function students() {
        fetch("http://localhost:9000/students")
            .then(res => res.json())
            .then(students => {
                const text = document.createTextNode(JSON.stringify(students));
                mainContent.replaceChildren(text)
            })
    }

    function studentDetails() {
        fetch("http://localhost:9000/students/10")
            .then(res => res.json())
            .then(student => {
                console.log(student)
                const text = document.createTextNode(JSON.stringify(student));
                mainContent.replaceChildren(text)
            })
    }

    function defaultRoute() {
        window.location.hash = "home"
    }
}