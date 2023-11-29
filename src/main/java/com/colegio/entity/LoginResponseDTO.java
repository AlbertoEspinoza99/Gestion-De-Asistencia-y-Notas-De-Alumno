package com.colegio.entity;

public class LoginResponseDTO {
	
	    private String user;
	    private String jwt;
        private String cargo;
	    
	    public LoginResponseDTO(){
	        super();
	    }

	    public LoginResponseDTO(String user, String jwt,String cargo){
	        this.user = user;
	        this.jwt = jwt;
	        this.cargo=cargo;
	    }

	    public String getUser(){
	        return this.user;
	    }

	    public void setUser(String user){
	        this.user = user;
	    }

	    public String getJwt(){
	        return this.jwt;
	    }

	    public void setJwt(String jwt){
	        this.jwt = jwt;
	    }

		public String getCargo() {
			return cargo;
		}

		public void setCargo(String cargo) {
			this.cargo = cargo;
		}

	    
}
