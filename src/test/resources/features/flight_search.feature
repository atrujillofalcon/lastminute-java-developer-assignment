Feature: the version can be retrieved
  Scenario: client makes call to GET /search
    When the client calls /search flights of 1 with origin in AMS and destination in FRA for departure date 01/08/2019
    Then the client receives status code of 200