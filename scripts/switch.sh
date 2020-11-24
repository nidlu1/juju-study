#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

function switch_proxy() {
    IDLE_PORT=$(find_idle_port)

    echo "> 전환할 Port: $IDLE_PORT"
    echo "> Port 전환"
    echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc

    echo "> 엔진엑스 Reload"
    sudo service nginx reload
}

#echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" : 하나의 문장을 만들어 파이프라인(|)으로 넘겨주기 위해 echo사용
#  엔진엑스가 변경할 프록시 주소를 설정.
#  쌍따옴표를 사용해야함 "", 사용하지 않으면 $service_url을 인식하지 못하고 변수를 찾게 됨
# | sudo tee /etc/nginx/conf.d/service-url.inc : 앞에서 넘겨준 문장을 service-url.inc에 덮어씀.
# sudo service nginx reload : 엔진엑스설정을 다시 불러온다. restart와 다르다. restart는 끊김현상이 있지만 reload는 끊김없음.
# 단 중요한 설정은 반영되지 않으므로 언젠간 restart를 사용해야함.
# 여기선 외부설정파일인 service-url을 다시 불러오는 거라 reload로 가능함.