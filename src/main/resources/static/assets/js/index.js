const {createApp} = Vue

const app = createApp({
    data() {
        return {
            shoesMain: [],
            emailUser: ''
        }
    },
    created() {
        this.loadData()
    },
    mount() {
        this.randomShoes()
    },
    methods: {
        loadData() {
            axios.get('/api/getProducts')
                .then(data => this.randomShoes(data.data))
        },
        randomShoes(data) {
            this.shoesMain = data.sort(() => Math.random() - 0.5).splice(0,4)
        },
        sendEmail() {
            axios.post('/api/sendEmail', `emailDestiny=${this.emailUser}`)
                .then(() => Swal.fire(
                    'You wil receive email in your email!',
                    '',
                    'success'
                ))
                .catch(() => Swal.fire({
                    icon: 'error',
                    title: 'The email is not valid',
                    text: '',
                }))
        },
        loginHome() {
            Swal.fire(
                'You must first log in',
                '',
                'question'
            )
            setTimeout(() => location.href = "../../login.html", 2000)
        }
    }
})

app.mount('#app')

function openNav() {
    document.getElementById("mySidebar").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
}

function closeNav() {
    document.getElementById("mySidebar").style.width = "0";
    document.getElementById("main").style.marginLeft = "0";
}