<script setup></script>

<template>
  <div
    class="modal fade"
    id="registerModal"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="staticBackdropLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-md">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">
            <i class="bi bi-person-check-fill">회원가입</i>
          </h4>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
          ></button>
        </div>

        <div class="modal-body">
          <form action="" id="registerForm">
            <div class="mb-3">
              <label class="form-label">아이디</label>
              <input
                type="text"
                class="form-control"
                id="registerId"
                placeholder="아이디 입력..."
                required
              />
            </div>
            <div class="mb-3">
              <label class="form-label">비밀번호</label>
              <input
                type="password"
                class="form-control"
                id="registerPw"
                placeholder="영문 숫자 포함 6자리 이상"
                required
              />
            </div>
            <div class="mb-3">
              <label class="form-label">이름</label>
              <input
                type="text"
                class="form-control"
                id="registerUserName"
                placeholder="User Name"
                required
              />
            </div>
            <div class="mb-3">
              <label class="form-label">주소</label>
              <input
                type="text"
                class="form-control"
                id="registerAddress"
                placeholder="address"
                required
              />
            </div>
            <div class="mb-3">
              <label class="form-label">전화번호</label>
              <input
                type="number"
                class="form-control"
                id="registerPh"
                placeholder="010-xxxx-xxxx"
                required
              />
            </div>
          </form>
        </div>

        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-primary btn-sm"
            onclick="registerUser()"
          >
            등록
          </button>
          <button
            type="button"
            class="btn btn-outline-danger btn-sm"
            data-bs-dismiss="modal"
          >
            Close
          </button>
        </div>
      </div>
    </div>
  </div>
  <!-- 회원가입 modal end -->

  <!-- 로그인 modal start -->
  <div
    class="modal fade"
    id="loginModal"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="staticBackdropLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-md">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">
            <i class="bi bi-person-fill">로그인</i>
          </h4>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
          ></button>
        </div>

        <div class="modal-body">
          <form action="">
            <div class="mb-3">
              <label for="id" class="form-label">아이디</label>
              <input
                type="text"
                class="form-control"
                id="user_id"
                placeholder="id 입력..."
                name="id"
              />
            </div>
            <div class="mb-3">
              <label for="password" class="form-label">비밀번호</label>
              <input
                type="password"
                class="form-control"
                id="user_pw"
                placeholder="pw 입력..."
                name="password"
              />
            </div>
          </form>
        </div>

        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-dark btn-sm"
            onclick="loginUser()"
          >
            로그인
          </button>
          <button
            type="button"
            class="btn btn-light btn-sm"
            data-bs-toggle="modal"
            data-bs-target="#findPwModal"
          >
            비밀번호 찾기
          </button>
        </div>
      </div>
    </div>
  </div>
  <!-- 로그인 modal end -->

  <!-- 회원 정보 확인/수정(마이페이지) modal start -->
  <div
    class="modal fade"
    id="userInfoModal"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="staticBackdropLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-md">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">
            <i class="bi bi-person-square">회원 정보 확인</i>
          </h4>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
          ></button>
        </div>

        <div class="modal-body">
          <form action="" class="form-gr">
            <div class="mb-3">
              <label class="form-label">아이디</label>
              <input type="text" class="form-control" id="userId" disabled />
            </div>
            <div class="mb-3">
              <label class="form-label">비밀번호</label>
              <input type="text" class="form-control" id="userPw" />
            </div>
            <div class="mb-3">
              <label class="form-label">이름</label>
              <input type="text" class="form-control" id="userName" />
            </div>
            <div class="mb-3">
              <label class="form-label">주소</label>
              <input type="text" class="form-control" id="userAddress" />
            </div>
            <div class="mb-3">
              <label class="form-label">전화번호</label>
              <input type="text" class="form-control" id="userPh" />
            </div>
          </form>
        </div>

        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-dark btn-sm"
            onclick="editUser()"
          >
            수정
          </button>
          <button
            type="button"
            class="btn btn-danger btn-sm"
            onclick="deleteUser()"
          >
            회원탈퇴
          </button>
          <button
            type="button"
            class="btn btn-outline-danger btn-sm"
            data-bs-dismiss="modal"
          >
            Close
          </button>
        </div>
      </div>
    </div>
  </div>
  <!-- 회원 정보 확인/수정(마이페이지) modal end -->

  <!-- 비밀번호 찾기 modal start -->
  <div
    class="modal fade"
    id="findPwModal"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="staticBackdropLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-md">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">
            <i class="bi bi-person-lines-fill">아이디 찾기</i>
          </h4>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
          ></button>
        </div>

        <div class="modal-body">
          <form id="findPwForm" class="form-gr">
            <div class="mb-3">
              <label class="form-label">이름</label>
              <input
                type="text"
                class="form-control"
                id="findName"
                placeholder="이름 입력..."
              />
            </div>
            <div class="mb-3">
              <label class="form-label">아이디</label>
              <input
                type="text"
                class="form-control"
                id="findId"
                placeholder="아이디 입력 ..."
              />
            </div>
            <div class="mb-3">
              <label class="form-label">전화번호</label>
              <input
                type="text"
                class="form-control"
                id="findPh"
                placeholder="010-xxxx-xxxx"
              />
            </div>
          </form>
        </div>

        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-dark btn-sm"
            onclick="findUserPw()"
          >
            비밀번호 찾기
          </button>
          <button
            type="button"
            class="btn btn-outline-danger btn-sm"
            data-bs-dismiss="modal"
          >
            Close
          </button>
        </div>
      </div>
    </div>
  </div>
  <!-- 비밀번호 찾기 modal end -->
</template>

<style scoped></style>
