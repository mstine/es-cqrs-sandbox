package contracts.widgets

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url '/widgets'
    }
    response {
        status 200
        body(
                [
                        [id: '1', name: 'Larry'],
                        [id: '2', name: 'Moe'],
                        [id: '3', name: 'Curly']
                ]
        )
        headers {
            contentType(applicationJson())
        }
    }
}