package org.kulturhusfx.base;

import java.util.Date;

public class Billett {

        private int telefonnrKjoper;
        private int plassnr;
        private Date kjopstidspunkt;

        public Billett(int telefonnrKjoper){
            this.telefonnrKjoper = telefonnrKjoper;
        }

        public void setTelefonnrKjoper(int telefonnrKjoper){
            this.telefonnrKjoper = telefonnrKjoper;
        }

        public int getTelefonnrKjoper() {
            return telefonnrKjoper;
        }

        public int genererPlass(){

            return 0;
        }
    }

