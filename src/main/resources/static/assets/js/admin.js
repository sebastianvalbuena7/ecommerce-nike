const { createApp } = Vue

const app = createApp({
    data() {
        return {
            arrayClients: [],
            arrayProducts: []
        }
    },
    created() {
        this.getClients()
        this.getProducts()
    },
    mount() {
    },
    methods: {
        getClients() {
            axios.get('/api/getClients')
                .then(response => this.arrayClients = response.data)
                .catch(error => console.error(error))
        },
        deleteClient(id) {
            Swal.fire({
                title: "Are you sure? You won't be able to revert this!",
                text: "",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.delete(`/api/deleteClient/${id}`)
                        .then(() => this.arrayClients = this.arrayClients.filter(client => client.id != id))
                        .catch(error => console.error(error))
                    Swal.fire(
                        'Deleted!',
                        'The client has been deleted.',
                        'success'
                    )
                }
            })
        },
        getProducts() {
            axios.get('/api/getProducts')
                .then(response => this.arrayProducts = response.data)
                .catch(error => console.error(error))
        },
        deleteProduct(id) {
            Swal.fire({
                title: "Are you sure? You won't be able to revert this!",
                text: "",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.delete(`/api/deleteProduct/${id}`)
                        .then(() => this.arrayProducts = this.arrayProducts.filter(product => product.id != id))
                        .catch(error => console.error(error))
                    Swal.fire(
                        'Deleted!',
                        'The product has been deleted.',
                        'success'
                    )
                }
            })
        },
        editProduct(id) {
            axios.put(`/api/editProduct/${id}`, {
                "price": 2500,
                "name": "Jordan Pink",
                "description": "Shoes very nice",
                "image": "https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/f2c0b0b1-9c95-4488-a86b-9324cee86472/calzado-air-max-tw-DLKBKm.png",
                "categoryShoes": "WOMEN",
                "sizeShoes": [1,2,5],
                "stock": 7,
                "collection": "Summer"
            })
                .then(() => console.log('Done!'))
                .catch(error => console.error(error))
        }
    }
})

app.mount('#app')
