import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url '/films'
        headers {
            header('Content-Type', 'application/json;charset=UTF-8')
        }
    }
    response {
        status OK()
        body("""[
  {
    "id": "12345678-1983-0000-0000-000000000000",
    "name": "Matrix 11",
    "type": "NEW"
  },
  {
    "id": "12345678-1984-0000-0000-000000000000",
    "name": "Spider Man",
    "type": "REGULAR"
  }
]
  """)
        headers {
            header('Content-Type': 'application/json;charset=UTF-8')
        }
    }
}