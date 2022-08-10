document.addEventListener('DOMContentLoaded',()=>{
	
	document.getElementById('uploadBtn').addEventListener('click',()=>{
		document.getElementById('uploadInput').click()
	})
	
		document.getElementById('uploadInput').addEventListener('change',()=>{
		document.getElementById('uploadForm').submit()
	})
var modalDialog = document.getElementById('uploadMessageModal')
	
	if(modalDialog){
		var modal = new bootstrap.Modal(modalDialog)
		modal.show()
	}
	
})