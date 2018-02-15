package contracts.widgets

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'DELETE'
        url '/widgets/1'
    }
    response {
        status 204
    }
}