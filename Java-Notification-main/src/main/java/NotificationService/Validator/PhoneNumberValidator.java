package NotificationService.Validator;


import NotificationService.Entity.Requests.SmsRequestDto;

public class PhoneNumberValidator {
    public static void validate(SmsRequestDto requestDto)
    {
        if(requestDto.getPhoneNumber()==null|| requestDto.getPhoneNumber().equals("")) {
            throw new IllegalArgumentException("Provide the phone number");
        }
        String phoneNumber= requestDto.getPhoneNumber();
        for(int i=0;i<phoneNumber.length();i++)
        {
            char c=phoneNumber.charAt(i);
            if(c>='0'&&c<='9')
                continue;
            else if(i==0&&c=='+')
                continue;
            else
                throw new IllegalArgumentException("Provide valid mobile number");
        }
    }
}
