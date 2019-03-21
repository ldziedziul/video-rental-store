import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url '/rentals'
        headers {
            header('Content-Type', 'application/json;charset=UTF-8')
        }
        body("""{
  "customerId": "12345678-1987-0000-0000-000000000000",
  "filmsToRent": [
    {
      "filmId": "12345678-1985-0000-0000-000000000000",
      "durationInDays": 3
    }
  ]
}
""")
    }
    response {
        status CREATED()
        body("""{
    "id": "12345678-1990-0000-0000-000000000000",
    "price": 123
  }
  """)
        headers {
            header('Content-Type': 'application/json;charset=UTF-8')
        }
    }
}