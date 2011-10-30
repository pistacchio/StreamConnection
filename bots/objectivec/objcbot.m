#import <Foundation/Foundation.h>

NSString* getLine()
{
    char _line[20];
    fgets(_line, 20, stdin);
    NSString *line = [[NSString stringWithCString:_line encoding:NSASCIIStringEncoding]
            stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    return line;
}

int main (int argc, const char * argv[])
{

    NSAutoreleasePool * pool = [[NSAutoreleasePool alloc] init];
    NSString *line;
    
    // insert code here...
    while (true)
    {
        line = getLine();
        
        if ([line isEqualToString:@"ready?"])
        {
            printf("ready!\n");
            fflush(stdout);
        }
        else if ([line isEqualToString:@"generate code"])
        {
            int shuffle = rand() % 25;
            for (int i = 0; i < 25; i++)
            {
                printf((i == shuffle) ? "#" : ".");
                if ((i+1) % 5 == 0) { printf("\n"); }
                fflush(stdout);
            }
        }
        else if ([line isEqualToString:@"find code"])
        {
            NSString *code = @"";
            for (int i = 0; i < 5; i++)
            {
                line = getLine();
                code = [NSString stringWithFormat:@"%@%@", code, line]; 
            }
            int sharp = [code rangeOfString:@"#"].location;
            int y = sharp / 5;
            int x = sharp - (y * 5);
            printf("%d %d\n", x, y);
            fflush(stdout);
        }
        else if ([line isEqualToString:@"bye!"])
        {
            printf("bye!\n");
            fflush(stdout);
            exit(0);
        }
    }
    [pool drain];
    return 0;
}

