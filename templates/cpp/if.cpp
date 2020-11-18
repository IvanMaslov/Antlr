#include<iostream>
int c;
int d;
int e;
int main(){
	c = 1;
	if (true){
		d = 2;
	}
	if (false){
		e = 3;
	}
	else{
		e = 4;
	}
	printf("%d", d);
	printf("%d", e);
}