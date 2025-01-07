// 주제 배열
const topics = [
  "연차 요청 이메일을 작성해보세요.",
  "회의 일정 조율 메신저를 작성해보세요.",
  "프로젝트 진행 상황 보고서를 작성해보세요.",
  "상사에게 피드백 요청 이메일을 작성해보세요.",
  "자료 요청 이메일을 작성해보세요.",
  "동료에게 협업 요청 메시지를 작성해보세요."
];

// 현재 주제 인덱스
let currentIndex = 0;

// 페이지 초기화
function init() {
  updateTopic();
}

// 현재 주제 업데이트
function updateTopic() {
  const currentTopic = document.getElementById("currentTopic");
  currentTopic.innerText = "주제: " + topics[currentIndex];
}

// 이전 주제 버튼 클릭 시
function prevTopic() {
  currentIndex = (currentIndex - 1 + topics.length) % topics.length; // 주기적으로 반복
  updateTopic();
}

// 다음 주제 버튼 클릭 시
function nextTopic() {
  currentIndex = (currentIndex + 1) % topics.length; // 주기적으로 반복
  updateTopic();
}

// 초기화 실행
init();


// AI 피드백 요청
async function sendMessage() {
  const message = document.getElementById('userMessage').value;
  const responseContainer = document.getElementById('responseContainer');

  if (!message.trim()) {
    responseContainer.innerHTML = "답변을 입력해주세요!";
    return;
  }

  responseContainer.innerHTML = "Loading...";

  try {
    const response = await fetch('/api/chat', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ message })
    });

    if (!response.ok) {
      throw new Error('Error with API request');
    }

    const data = await response.json();
    responseContainer.innerHTML = `<strong>Response:</strong><br>${data['openai(chatGPT) 응답']}`;
  } catch (error) {
    responseContainer.innerHTML = `<strong>Error:</strong> ${error.message}`;
  }
}

