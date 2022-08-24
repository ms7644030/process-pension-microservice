# process-pension-microservice

port = 8081

#AWS ECS SERVICE CONFIGURATION

path = /api/process-pension-service/*
health =/api/process-pension-service/manage/health

# Container Config Environment Variable

AUTHORIZATION_SERVICE_URI
PENSIONER_DETAIL_URI
