// 네비게이션 및 캐러셀 UI를 위한 DOM 요소
let nextDom = document.getElementById("next");
let prevDom = document.getElementById("prev");
let carouselDom = document.querySelector(".carrier-carousel");
let listItemDom = document.querySelector(
  ".carrier-carousel .carrier-carousel-list"
);
let thumbnailDom = document.querySelector(
  ".carrier-carousel .carrier-thumbnail"
);

// 애니메이션 및 자동 전환을 위한 타이밍 변수
let timeRunning = 3000; // 애니메이션 실행 시간
let timeAutoNext = 7000; // 다음 항목으로 자동으로 이동하기 전의 시간

// 애니메이션이 끝난 후 전환 클래스를 제거하기 위한 타임아웃
let runTimeOut = setTimeout(() => {
  carouselDom.classList.remove("next");
  carouselDom.classList.remove("prev");
}, timeRunning);

// 다음 항목으로 자동으로 이동하기 위한 타임아웃
// let runAutoRun = setTimeout(() => {
//   nextDom.click();
// }, timeAutoNext);

// 다음 및 이전 버튼 클릭 이벤트
nextDom.onclick = function () {
  showSlider("next");
};
prevDom.onclick = function () {
  showSlider("prev");
};

// 슬라이더 표시 기능
function showSlider(type) {
  // 캐러셀 아이템 및 썸네일 아이템 선택
  let itemSlider = document.querySelectorAll(
    ".carrier-carousel .carrier-carousel-list .carrier-carousel-item"
  );
  let itemThumbnail = document.querySelectorAll(
    ".carrier-carousel .carrier-thumbnail .carrier-carousel-item"
  );

  // 'next' 또는 'prev'에 따라 아이템을 적절히 이동
  if (type === "next") {
    listItemDom.appendChild(itemSlider[0]); // 첫 번째 아이템을 마지막으로 이동
    thumbnailDom.appendChild(itemThumbnail[0]); // 첫 번째 썸네일을 마지막으로 이동
    carouselDom.classList.add("next"); // 'next' 애니메이션 클래스 추가
  } else {
    let positionLastItem = itemSlider.length - 1; // 마지막 아이템의 위치 계산
    listItemDom.prepend(itemSlider[positionLastItem]); // 마지막 아이템을 첫 번째로 이동
    thumbnailDom.prepend(itemThumbnail[positionLastItem]); // 마지막 썸네일을 첫 번째로 이동
    carouselDom.classList.add("prev"); // 'prev' 애니메이션 클래스 추가
  }

  // 이전 타임아웃을 초기화하여 연속 클릭에 대응
  clearTimeout(runTimeOut);
  // clearTimeout(runAutoRun);
}
document.addEventListener("wheel", function (event) {
  // deltaY 프로퍼티를 사용하여 스크롤 방향 판별
  // 아래로 스크롤 (양의 값): 다음 슬라이드
  if (event.deltaY > 0) {
    nextDom.click();
  }
  // 위로 스크롤 (음의 값): 이전 슬라이드
  else if (event.deltaY < 0) {
    prevDom.click();
  }
});
