package numbers;

public enum DigitTypes implements Checable{
    ODD{
        public boolean isType(long number){
            return number%2 == 1? true: false;
        }
    },

    EVEN{
        public boolean isType(long number){
            return number%2 == 1? false: true;
        }
    },

    PALINDROMIC{
        public boolean isType(long number){
            if(number<10){
                return true;
            }

            long controlCopyNumber = number;
            long remainder = 0;
            long mirrorNumber = 0;

            if(number%10 == 0){
                return false;
            }else if(number%10 == number){
                return true;
            }else{
                while (true){
                    remainder = number%10;
                    mirrorNumber = mirrorNumber*10 + remainder;
                    if (remainder == number){
                        break;
                    }
                    number = (number - remainder)/10;
                }
            }

            if (controlCopyNumber == mirrorNumber){
                return true;
            }
            return false;
        }
    },

    GAPFUL{
        public boolean isType(long number){
            long leftNumber = number%10;
            long rightNumber = 0;
            long doubleNumber = number;
            int count = 0;

            while (true){
                rightNumber = number/10;
                number = rightNumber;
                count++;
                if(rightNumber<10){
                    break;
                }
            }

            long gapf = rightNumber*10 + leftNumber;

            if(count<2){
                return false;
            }

            if(doubleNumber % gapf == 0){
                return true;
            }else{
                return false;
            }
        }
    },

    SPY{
        public boolean isType(long number){
            if(number<10){
                return true;
            }

            long sum = 0;
            long multiple = 1;
            long workingNumber;
            long base = number;

            while (true){
                workingNumber = base%10;
                base = base/10;
                sum += workingNumber;
                multiple*=workingNumber;

                if(base<10){
                    sum+=base;
                    multiple *=base;
                    break;
                }
            }

            if(sum == multiple){
                return true;
            }else{
                return false;
            }
        }
    },

    DUCK{
        public  boolean isType(long number){
            if(number/10 != 0){
                for(int i=10; ;){
                    if(number%i == number){
                        return false;
                    }else if(number%i == 0){
                        return true;
                    }else{
                        number = number - number%10;
                        number = number/10;
                    }
                }
            }
            return false;
        }
    },

    SUNNY{
        public boolean isType(long number){
            if(number == 1){
                return false;
            }else if(Math.sqrt(number + 1)%1 == 0){
                return true;
            }
            return false;
        }
    },

    SQUARE{
        public boolean isType(long number){
            if((Math.sqrt(number))%1 == 0){
                return true;
            }
            return false;
        }
    },

    BUZZ{
        public boolean isType(long number){
            long remainder = number%10;
            if (remainder == 7 || number %7 == 0){
                return true;
            }
            return false;
        }
    },

    JUMPING{
        public boolean isType(long number){
            boolean marker = false;

            if(number<10){
                return true;
            }

            long rightNumber = number%10;
            number = number/10;
            long leftNumber = number%10;

            while (true){
                if((leftNumber-rightNumber == 1 || leftNumber-rightNumber == -1)){
                    marker = true;
                }else{
                    return false;
                }

                if (number == leftNumber && (leftNumber-rightNumber == 1 || leftNumber-rightNumber == -1)){
                    return true;
                }

                rightNumber = leftNumber;
                number = number/10;
                leftNumber = number%10;
            }
        }
    },

    HAPPY{
        public boolean isType(long number){
            int numberSum = 0;
            long x = number;
            boolean marker = true;

            long rightNumber = 0;

            while (marker){
                while (marker){
                    rightNumber = number%10;
                    numberSum += rightNumber*rightNumber;
                    number = number/10;

                    if(number<10){
                        numberSum +=number*number;
                        break;
                    }
                }

                if(numberSum<10){
                    if(numberSum == 1){
                        return true;
                    }else if(numberSum>1 && numberSum<=9){
                        return false;
                    }
                }else {
                    number = numberSum;
                    numberSum = 0;
                }
            }

            return false;
        }
    },

    SAD{
        public boolean isType(long number){
            int numberSum = 0;
            long x = number;
            boolean marker = true;

            long rightNumber = 0;

            while (marker){
                while (marker){
                    rightNumber = number%10;
                    numberSum += rightNumber*rightNumber;
                    number = number/10;

                    if(number<10){
                        numberSum +=number*number;
                        break;
                    }
                }

                if(numberSum<10){
                    if(numberSum == 1){
                        return false;
                    }else if(numberSum>1 && numberSum<=9){
                        return true;
                    }
                }else {
                    number = numberSum;
                    numberSum = 0;
                }
            }

            return true;
        }

    };
}