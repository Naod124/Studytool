package com.company.studytool.Model;

import java.util.ArrayList;

public class PrepareQuiz {

    public static void prepareDsQuiz(ArrayList<QuestionModel> quizList) {
        QuestionModel question1 = new QuestionModel("Two main measures for the efficiency of an algorithm are", "Processor and memory",
                "Complexity and capacity", "Time and space", "Data and space", "Time and space");
        QuestionModel question2 = new QuestionModel("The time factor when determining the efficiency of algorithm is measured by",
                "Counting the number of key operations", "Counting the number of statements", "Counting microseconds",
                "Counting the kilobytes of algorithm", "Counting the number of key operations");
        QuestionModel question3
                = new QuestionModel("The space factor when determining the efficiency of algorithm is measured by",
                "Counting the maximum disk space needed by the algorithm", "Counting the average memory needed by the algorithm",
                "Counting the maximum memory needed by the algorithm", "Counting the minimum memory needed by the algorithm", "Counting the maximum memory needed by the algorithm");
        QuestionModel question4 = new QuestionModel("Which of the following cases does not exist in complexity theory", "Best case ",
                "Worst case", "Average case", "Null case", "Null case");
        QuestionModel question5 = new QuestionModel("The Worst case occur in linear search algorithm when", " Item is the last element in the array or is not there at all",
                "Item is not in the array at all ", " Item is the last element in the array ", "Item is somewhere in the middle of the array",
                "Item is the last element in the array or is not there at all");
        QuestionModel question6 = new QuestionModel("The Average case occur in linear search algorithm", "When Item is the last element in the array or is not there at all",
                "When Item is the last element in the array ", "When Item is somewhere in the middle of the array",
                "When Item is not in the array at all ", "When Item is somewhere in the middle of the array");
        QuestionModel question7 = new QuestionModel("The complexity of the average case of an algorithm is", "Much more simpler to analyze than that of worst case",
                "Much more complicated to analyze than that of worst case", "None of them ",
                "Sometimes more complicated and some other times simpler than that of worst case", "Much more complicated to analyze than that of worst case");
        QuestionModel question8 = new QuestionModel("The complexity of linear search algorithm is", "O(log n)", "O(n2)", "O(n log n) ", "O(n)", "O(n)");
        QuestionModel question9 = new QuestionModel("The complexity of Binary search algorithm is", "O(log n)", "O(n log n)", "O(n2)", "O(n)", "O(log n)");
        QuestionModel question10 = new QuestionModel(" The complexity of Bubble sort algorithm is", "O(n log n)", "O(log n)", "O(n2)", "O(n)", "O(n2)");
        QuestionModel question11 = new QuestionModel("The complexity of merge sort algorithm is", "O(n)", "O(n log n)", "O(n2)", "O(log n)", "O(n log n)");
        QuestionModel question12 = new QuestionModel(" The complexity of selection sort algorithm is", "O(log n)", "O(n)", "O(n log n)", "O(n2)", "O(n2)");
        QuestionModel question13 = new QuestionModel("What is the worst case running time complexity of merge sort?", "O(n log n)", "O(n)",
                "O(log n)", "O(n2)", "O(n log n)");
        QuestionModel question14 = new QuestionModel("What is the best case running time complexity of quicksort?", "O(n2)", "O(n)", "O(n log n)", "O(log n)", "O(n log n)");
        QuestionModel question15 = new QuestionModel("What is the average case running time complexity of quicksort?", "O(n2)", "O(n log n)", "O(log n)", "O(n)", "O(n log n)");
        QuestionModel question16 = new QuestionModel("The best case running time  complexity of insertion sort algorithm is", "O(n log n)", "O(n2)",
                "O(log n)", "O(n)", "O(n)");
        QuestionModel question17 = new QuestionModel("A sort which compares adjacent elements in a list and switches where necessary is", "bubble sort",
                "quick sort", "insertion sort", "selection sort", "bubble sort");
        QuestionModel question18 = new QuestionModel("As part of the maintenance work, you are entrusted with the work of rearranging the library books in a shelf in proper order, at the end of each day. The ideal choice will be",
                "bubble sort", "Merge sort", "Insertion sort", "Selection sort", "Insertion sort");
        QuestionModel question19 = new QuestionModel("Which among the following is the best when the list is already sorted", "Selection sort",
                " Merge sort", "Bubble sort", "Insertion sort", "Insertion sort");
        QuestionModel question20 = new QuestionModel("You have a sorted array and now you are given an element to be placed in that array so that the resulting array is also sorted, the best sorting technique in this case is",
                " bubble sort", "Insertion sort", "quick sort", "merge sort", "Insertion sort");
        QuestionModel question21 = new QuestionModel("The running time of quick sort largely depends on",
                "selection of pivot element", "size of element ", "number of inputs", "arrangement of elements", "selection of pivot element ");
        quizList.add(question1);
        quizList.add(question2);
        quizList.add(question3);
        quizList.add(question4);
        quizList.add(question5);
        quizList.add(question6);
        quizList.add(question7);
        quizList.add(question8);
        quizList.add(question9);
        quizList.add(question10);
        quizList.add(question11);
        quizList.add(question12);
        quizList.add(question13);
        quizList.add(question14);
        quizList.add(question15);
        quizList.add(question16);
        quizList.add(question17);
        quizList.add(question18);
        quizList.add(question19);
        quizList.add(question20);
        quizList.add(question21);
    }

    public static void prepareDcQuiz(ArrayList<QuestionModel> quizList) {

        QuestionModel question1 = new QuestionModel("Local DNS name servers", "obtain resource records from Web caches",
                "cache resource records and never discard them ", "never cache resource records", "cache resource records, but discard them after a period of time that is on the order of a few days", "cache resource records, but discard them after a period of time that is on the order of a few days");
        QuestionModel question2 = new QuestionModel("Which application layer protocol correctly match a corresponding function?", "SMTP supports file sharing",
                "DNS dynamically allocates IP addresses to hosts", "HTTP transfers data from a web server to a client", "POP delivers email from the client to the email server", "HTTP transfers data from a web server to a client ");
        QuestionModel question3 = new QuestionModel("Which protocol is used to control the transfer of web resources from a web server to a client browser?", "TCP",
                "HTML", "HTTP", "IP", "HTTP");
        QuestionModel question4 = new QuestionModel("What IS NOT protocols operate at the Application layer of the OSI model?", "HTTP",
                "IP", "FTP", "SMTP", "IP");
        QuestionModel question5 = new QuestionModel("Which layer of the OSI model do packets belong to?", "Data link ",
                "Network", "Presentation", "Transport", "Network");
        QuestionModel question6 = new QuestionModel("An RFC is defined by the", "IETF",
                "UN", "ANSI", "ISO", "IETF");
        QuestionModel question7 = new QuestionModel("a DNS server can return a different IP address for a given name, depending on whether the lookup specifies email or web service.", "No",
                "ISO", "depends", "true", "true");
        QuestionModel question8 = new QuestionModel("Which layer of the OSI model supplies services that allow user to interface with the network services?", "transport layer ",
                "network layer ", "application layer ", "pyshical layer ", "application layer ");
        QuestionModel question9 = new QuestionModel("What application layer protocol is commonly used to support for file transfers between a client and a server?", "FTP",
                "presentation, data link, session, transport, network, physical, application", "HTML", "HTTP", "FTP");
        QuestionModel question10 = new QuestionModel("What is the proper order of the layers of the OSI model from the highest layer to the lowest layer?", "physical, network, application, data link, presentation, session, transport ",
                "application, presentation, session, transport, network, data link, physical", " application, presentation, physical, session, data link, transport, network", "application, physical, session, transport, network, data link, presentation", "application, presentation, session, transport, network, data link, physical");
        QuestionModel question11 = new QuestionModel("TCP provides the stream transport and is a reliable transport service.", "false",
                "true", "non", "non", "true");
        QuestionModel question12 = new QuestionModel("Suppose a client sends an HTTP request message with the If-modified-since:header. Suppose the object in a server has not changed since the last time a client retrieved the object. Then the server will send a response message with the status code:", "none of them ",
                "304 Not Modified", "404 Not Found ", "200 OK ", "304 Not Modified ");
        QuestionModel question13 = new QuestionModel("a multi-national company can choose to divide its domain name hierarchy in such a way that the company has a domain name server in Europe, one in Asia, and one in North America.", "false",
                "true", "non", "non", "true");
        QuestionModel question14 = new QuestionModel("Which layer of the OSI 7 layer reference model has the same function as the TCP/IP model network interface layer?", "data link layer ",
                "session layer", "transport layer", "network layer", "session layer");
        QuestionModel question15 = new QuestionModel("Which of the following belongs to the presentation layer in the OSI model?", "TCP",
                "HTTP", "  MIDI & JPEG ", "SSH", "HTTP");
        QuestionModel question16 = new QuestionModel("Encapsulation is the process of", "Taking headers off of data ",
                "none of the answers given", "taking headers off of incoming info", "Adding headers to incoming information", "Adding headers to incoming information ");
        QuestionModel question17 = new QuestionModel("Which of the following protocol(s) belong(s) to the transport layer?", "IP",
                "SMTP", "HTTP", "non", "TCP & UDP");
        QuestionModel question18 = new QuestionModel("Layers four and five of the Internet protocol stack are implemented in the end systems but not in the routers in the network core.", "false",
                "non", "non", "non", "true");
        QuestionModel question19 = new QuestionModel("What is the role of the OSI application layer?", "provides encryption and conversion of data ",
                "provides control of all the data flowing between the source and destination devices", "provides segmentation of data ", "provides the interface between the applications on either end of the network", "provides the interface between the applications on either end of the network");
        QuestionModel question20 = new QuestionModel("Given an URL of http://www.tec.hkr.se/moodle/index.html. What is the top-level domain of this URL?", "http://",
                "non", "moodle", "hkr.se ", "se");
        QuestionModel question21 = new QuestionModel("Protocol information is transferred with data in a", "character",
                "non", "non", "non", "header");
        QuestionModel question22 = new QuestionModel("what protocol is used to transfer web pages from server to client?", "POP",
                "HTML", "cable", "URL", "HTTP");
        QuestionModel question23 = new QuestionModel("Decapsulation is the process of", "Taking headers off of outgoing data ",
                "non", "Adding headers to outgoing information", " Adding headers to incoming information", "Taking headers off of incoming information");
        QuestionModel question24 = new QuestionModel("SMTP is used to", "to define the format of message headers ",
                "non", "to transfer messages from mail server to a user agent", "all of them", "to transfer messages from one mail server to another");
        QuestionModel question25 = new QuestionModel("a web server must have a domain name that begins with www.", "true",
                "non", "non", "false", "false");
        QuestionModel question26 = new QuestionModel("What is the primary purpose of Layer 4 port number?", "to identify devices on the local media ",
                "to identify the source and destination end devices that are communicating", "to identify the hops between source and destination", "to identify the applications or services that are communicating within the end devices", "to identify the applications or services that are communicating within the end devices");
        QuestionModel question27 = new QuestionModel("Which layer encapsulates the segment into packets?", "transport layer ",
                "network interface layer", "Internet layer ", "physical layer", "network interface layer");
        QuestionModel question28 = new QuestionModel("What purpose do protocols serve in computer networking?", "Decide how computers perform internal processing",
                "non", "Decide who gets information first ", "Decide how fast computers can send and receive data", "Provide rules for communicating computers ");
        QuestionModel question29 = new QuestionModel("UDP provides the message transport service. It is connectionless service.", "false",
                "true", "non", "non", "true");
        QuestionModel question30 = new QuestionModel("The Internet is an example of", "a circuit network.",
                "non", "a packet switched network", "a radio signal based network. ", "a packet switched network. ");
        QuestionModel question31 = new QuestionModel("Why are port numbers included in the TCP header of a segment?", "to allow the receiving host to assemble the packet in the proper order",
                "to identify which switch ports should receive or forward the segment", "to determine which Layer 3 protocol should be used to encapsulate the data", "to indicate the correct router interface that should be used to forward a segment", "to enable a receiving host to forward the data to the appropriate application");
        quizList.add(question1);
        quizList.add(question2);
        quizList.add(question3);
        quizList.add(question4);
        quizList.add(question5);
        quizList.add(question6);
        quizList.add(question7);
        quizList.add(question8);
        quizList.add(question9);
        quizList.add(question10);
        quizList.add(question11);
        quizList.add(question12);
        quizList.add(question13);
        quizList.add(question14);
        quizList.add(question15);
        quizList.add(question16);
        quizList.add(question17);
        quizList.add(question18);
        quizList.add(question19);
        quizList.add(question20);
        quizList.add(question21);
        quizList.add(question22);
        quizList.add(question23);
        quizList.add(question24);
        quizList.add(question25);
        quizList.add(question26);
        quizList.add(question27);
        quizList.add(question28);
        quizList.add(question29);
        quizList.add(question30);
        quizList.add(question31);
    }

    public static void prepareOsQuiz(ArrayList<QuestionModel> quizList) {
        QuestionModel question1 = new QuestionModel("Which disk is used to cold boot a PC?", "System disk",
                "System dis", "Diagnostic disk", "Program disk", "System disk");
        QuestionModel question2 = new QuestionModel("What is the name of the program that controls the overall functions of computer?", "A browser",
                "The file manager", "An application program", " The operating system", " The operating system");
        QuestionModel question3 = new QuestionModel("hich of the following is/are file extension(s) in DOS?", "COM",
                "All of these", "BAT", "EXE", "All of these");
        QuestionModel question4 = new QuestionModel("A process having multiple threads of control implies", "Only one thread per process to use",
                "More than one task at a time", "Only one task at a time, but much faster", "All of the above", "More than one task at a time");
        QuestionModel question5 = new QuestionModel("Which command is used to set a name to a disk in DOS?", "DISKLABEL",
                "VOL", "VOLUME", "LABEL", "LABEL");
        QuestionModel question6 = new QuestionModel("What is the maximum length allowed for primary name of a computer file under DOS?", "3",
                "5", "12", "8", "5");
        QuestionModel question7 = new QuestionModel("Maximum length of DOS command using any optional parameter is", "26 characters",
                "156 characters", "87 characters", "127 characters", "127 characters");
        QuestionModel question8 = new QuestionModel("Which command is used to clear the screen and display the operating system prompt on the first line of the display?", "Cd",
                "Cls", "Rename", "Md", "Cls");
        QuestionModel question9 = new QuestionModel("Delete directories", "skd, srbm",
                "rd, rmdir", "all of these", "nd, ndi", "rd, rmdir");
        QuestionModel question10 = new QuestionModel("The primarily takes care of the behind the scenes details and manages the hardware", "Hard disk",
                "Peripheral devices", "Application software", "Operating system", "Operating system");
        QuestionModel question11 = new QuestionModel("The capabilities of the operating system is to enable two or more than two programs to execute simultaneously in a single computer system by a single processor is", "Multi-execution",
                "Multi-programming", "Multi-tasking", "Multi-processing", "Multi-programming");
        QuestionModel question12 = new QuestionModel("While working with MS-DOS which command is used to more file from one directory to another?", "Cp",
                "Rename", "Copy", "Move", "Move");
        QuestionModel question13 = new QuestionModel("Characteristics of an operating system is/are", "Error recovery",
                "All of the above", "Memory management", "Resource management", "All of the above");
        QuestionModel question14 = new QuestionModel("To display the list of all the file on the disk you would type", "DIR AUTOEXEC.BAT",
                "DIR FILES", "DIR", "COPY", "DIR");
        QuestionModel question15 = new QuestionModel("Internal commands in DOS are", "Time, type, dir",
                " Del, disk, copy, label", " Dir, ren, sys", "Cls, rd label", " Time, type, dir");

        quizList.add(question1);
        quizList.add(question2);
        quizList.add(question3);
        quizList.add(question4);
        quizList.add(question5);
        quizList.add(question6);
        quizList.add(question7);
        quizList.add(question8);
        quizList.add(question9);
        quizList.add(question10);
        quizList.add(question11);
        quizList.add(question12);
        quizList.add(question13);
        quizList.add(question14);
        quizList.add(question15);


    }
}
