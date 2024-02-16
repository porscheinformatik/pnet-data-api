#!/bin/bash

LOGIN=n
ABOUT=n
GET=

ENVIRONMENT=qa
URL=
USERNAME=
TOKEN=
REPEAT=

function usage () {
    echo "Performs Data API queries."
    echo
    echo "l, login                    Performs a login and stores the token locally."
    echo "  -e, --environment [ARG]   Sets the environment: \"qa\" (default) or \"prod\"".
    echo "  -t, --token [ARG]         The authentication token as specified in the Selfservice."
    echo "  -u, --username [ARG]      The username as specified in the Selfservice."
    echo
    echo "a, about                    Perform an about request."
    echo
    echo "g, get [ARG]                Perform a custom GET request (starting after /api/v1/)."
    echo "  -r, --repeat [N]          Repeat the query N times."
    echo
    echo "?, help                     Print this help."
    echo
    echo "Examples:"
    echo
    echo "Perform a login:"
    echo "./data-api.sh l"
    echo
    echo "Find all functions (perform a login beforehand) and write the response to a file:"
    echo "./data-api.sh g \"functions/find?l=de&pp=25\" > response.json"
    echo
    exit 0
}

function get () {
    JWT=$(<token)
    RESPONSE=$(curl -H "$JWT" -w "\n%{http_code}" -X GET "$URL/api/v1/$1")

    if [ $? -ne 0 ]
    then
        echo "Query failed."
        echo $RESPONSE
        exit 1
    fi

    HTTP_CODE=$(tail -n1 <<< "$RESPONSE")
    CONTENT=$(sed '$ d' <<< "$RESPONSE")

    if [[ "$HTTP_CODE" -ne 200 ]]
    then
        echo "Query failed: $HTTP_CODE"
        echo $RESPONSE
        exit 1
    fi

    echo $CONTENT
    echo
}

if [ $# == 0 ]
then
    usage
fi

while [ $# != 0 ]
do
    case "$1" in
    l|login) LOGIN=y ;;
    a|about) ABOUT=y ;;
    g|get) shift; GET="$1" ;;
    -e|--environment) shift; ENVIRONMENT="$1" ;;
    -t|--token) shift; TOKEN="$1" ;;
    -u|--username) shift; USERNAME="$1" ;;
    -r|--repeat) shift; REPEAT="$1" ;;
    -?|--help) usage;;
    --) shift; break;;
    *) echo "Invalid parameter: $1"
       usage;;
    esac
    shift
done

if [ -z "$ENVIRONMENT" ]
then
    read -p "Which environment? [qa, prod] " ENVIRONMENT
fi

case $ENVIRONMENT in
    qa) URL=https://qa-data.auto-partner.net/data;;
    prod) URL=https://data.auto-partner.net/data;;
    *) echo Invalid option: $environment; exit -1 ;;
esac

if [ -z "$REPEAT" ]
then
    REPEAT=1
fi

if [ "${LOGIN}" == "y" ]
then
    echo
    echo Perform Login
    echo =============
    echo
    echo "URL: $URL"
    echo

    if [ -z "$TOKEN" ] && [ -z "$USERNAME" ]
    then
        read -p "Authentication token or username: " TOKEN_OR_USERNAME

        if [ ${#TOKEN_OR_USERNAME} -ge 64 ]
        then
            # most likely a token
            echo "Tokens do not work, currently :("
            exit -1
            TOKEN=$TOKEN_OR_USERNAME
        else
            USERNAME=$TOKEN_OR_USERNAME
        fi
    fi

    if [ -n "$TOKEN" ]
    then
        echo

        RESPONSE=$(curl -i -X POST $URL/login -H "Accept: application/json" -H "Authentication: Bearer $TOKEN")
    else
        read -s -p "Password: " PASSWORD

        echo
        echo

        RESPONSE=$(curl -i -X POST $URL/login -d "{\"username\":\"$USERNAME\",\"password\":\"$PASSWORD\"}")
    fi

    if [ $? -ne 0 ]
    then
        echo "Command failed."
        echo $RESPONSE
        exit 1
    fi

    echo
    echo $RESPONSE
    echo

    JWT=$(echo "$RESPONSE" | grep -i authorization:)

    echo $JWT > token
fi

if [ "${ABOUT}" == "y" ]
then
    get "about"
fi

if [ -n "${GET}" ]
then
    for i in $(seq 1 $REPEAT)
    do
        get "$GET"
    done
fi
