document.addEventListener('DOMContentLoaded', (event) => {

    let menuIsOpen = false;
    const hamburger = document.getElementById('hamburger');
    const navLinks = document.querySelector(".nav-links")
    const nav = document.querySelector(".nav");


    hamburger.addEventListener('click', () => {
        menuIsOpen = !menuIsOpen;
        if (menuIsOpen) {
            navLinks.classList.add('show')
            hamburger.textContent = "X";
        } else {
            navLinks.classList.remove('show')
            hamburger.textContent = "|||";
        }
    })

    document.addEventListener('click', (event) => {

        if (menuIsOpen && !navLinks.contains(event.target) && !hamburger.contains(event.target)) {
            navLinks.classList.remove('show')
            hamburger.textContent = "|||";
            menuIsOpen = false
        }

    });


})


async function getData() {
    try {
        const request = new Request(
            "http://localhost:8080/api/movie?pageNo=0&pageSize=10", {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                },
            })

        const response = await fetch(request);

        if (!response.ok) {
            console.error("Could not fetch data.");
        }

        const data = await response.json();
        return data.content

    } catch (error) {
        console.log(error)
    }
}

async function getMovieData() {
    getData().then((data) => {
        for (const movie of data) {
            let movieCard = createMovieCard(movie);
            const container = document.getElementById("container");
            container.insertAdjacentHTML('beforeend', movieCard);
        }
    })

}

getMovieData()


    function createMovieCard(movie) {
    const html =
    `
        <div id="movie-card" class="card" style="background-image: url(http://localhost:8080${movie.poster})">
            <div class="top">
            </div>
            <div class="bottom">
                <div class="title">
                    <h2>${movie.title}</h2>
                    <div class="meta">
                        <span>${movie.studio}</span>
                        <span>${movie.year}</span>
                        <span>Rated ${movie.rating}</span>
                    </div>
                </div>
                <p class="card-content">${movie.description}</p>
                <a class="btn" href="#">Review</a>
            </div>
    `
    return html;
}

