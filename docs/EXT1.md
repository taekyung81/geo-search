### Question.

> 구글 장소 검색 등 새로운 검색 API 제공자의 추가 시 변경 영역 최소화에 대한 고려

---

#### 최초

* 처음에는 카카오, 네이버만을 생각하고 작업함
* 이후에 구글이 추가될 경우 '모든 API 에 포함된 장소를 찾는' 로직이 계속 변경되어야 함을 인지함

---

#### 현재 로직

* '카카오'를 기준으로 '다른 N개의 데이터 셋'에서 동일한 장소가 전부 다 있는지 확인하는 형태로 '아이디어만 변경함'
* 즉
    * 예전: 카카오.contain(A) && 네이버.contain(B)
    * 개선: (카카오,네이버,구글) 의 교집합을 찾는 형태로 생각
* 로직
    * 모든 API를 호출한다 [여기](https://github.com/taekyung81/geo-search/blob/master/gs-core/src/main/java/com/bistros/gs/application/search/service/PlaceApiCaller.java#L26-L36)
    * 전체 데이터셋에 존재하는 장소를 1순위로 노출한다 (교집합) [여기](https://github.com/taekyung81/geo-search/blob/master/gs-core/src/main/java/com/bistros/gs/application/search/service/PlaceCollector.java#L33)
    * 카카오 결과에서 교집합을 제외한 나머지를 2순위로 노출한다 (차집합)
    * 네이버 결과에서 교집합을 제외한 나머지를 3순위로 노출한다 (차집합)
    * (만약) 구글이 구현되어 있다면 역시 교집합을 제외하고 노출한다 (차집합)

#### 구글 검색이 추가된다면?

* 장소를 찾는 로직의 변경은 없이 '구글 API'를 구현하고 Spring Bean 으로 등록한다. [여기](https://github.com/taekyung81/geo-search/blob/master/gs-external/src/main/java/com/bistros/gs/remote/api/GoogleOpenApiAdapter.java)
* 구글이 추가된다면 "세 곳에 존재", "카카오를 제외하고 두 곳에 존재", "카카오를 포함하고 두 곳에 존재" 등등.. 다영한 경우의 수가 있고 그 경우의 수는 문제에서 제시되어 있지 않기 때문에 *요구 사항이
  변경되어 로직이 변경될 수는 있다*
      
