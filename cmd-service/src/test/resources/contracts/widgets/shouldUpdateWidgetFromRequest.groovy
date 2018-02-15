package contracts.widgets

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'PUT'
        url $(consumer(regex('^/widgets/[0-9]{1}$')), producer('/widgets/1'))
        body(
                id: $(consumer(regex(number())), producer('1')),
                name: $(consumer(regex(nonEmpty())), producer('Larry'))
        )
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 201
        body(
                id: fromRequest().body('$.id'),
                name: fromRequest().body('$.name')
        )
        headers {
            contentType(applicationJson())
        }
    }
}